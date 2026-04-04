package com.gobdev.spring_mongodb_social_api.resources;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gobdev.spring_mongodb_social_api.domain.Post;
import com.gobdev.spring_mongodb_social_api.dto.CommentDTO;
import com.gobdev.spring_mongodb_social_api.dto.CommentUpdateInsertDTO;
import com.gobdev.spring_mongodb_social_api.dto.PostPageDTO;
import com.gobdev.spring_mongodb_social_api.dto.PostUpdateInsertDTO;
import com.gobdev.spring_mongodb_social_api.resources.util.URL;
import com.gobdev.spring_mongodb_social_api.services.PostService;

import jakarta.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping(value = "/posts")
public class PostResource {

    @Autowired
    private PostService service;

    @Autowired
    private ModelMapper modelMapper;


    // POST METHODS

    @GetMapping 
    public ResponseEntity<List<Post>> findAll() {
        List<Post> posts = service.findAll();

        return ResponseEntity.ok().body(posts);
    }

    @GetMapping(value = "/{id}") 
    public ResponseEntity<Post> findById(@PathVariable String id) {
        Post post = service.findById(id);

        return ResponseEntity.ok().body(post);
    }

    @PostMapping(value = "/{userId}") 
    public ResponseEntity<Post> insert(@PathVariable String userId, @Valid @RequestBody PostUpdateInsertDTO objDTO) {
        Post post = modelMapper.map(objDTO, Post.class);
        post = service.insert(post, userId);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(post.getId())
            .toUri();

        return ResponseEntity.created(uri).body(post);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@PathVariable String id, @RequestBody PostUpdateInsertDTO objDTO) {
        Post post = modelMapper.map(objDTO, Post.class);
        post.setId(id); // Ensure the ID is set for the update operation
        service.update(post);

        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/titlesearch") // posts/titlesearch?text=bom%20dia
    public ResponseEntity<List<Post>> findByTitle(@RequestParam(value = "text", defaultValue = "") String text) {
        text = URL.decodeParam(text);
        List<Post> posts = service.findByTitle(text);

        return ResponseEntity.ok().body(posts);
    }

    @GetMapping(value = "/authorsearch") // posts/authorsearch?authorName=Maria%20brown
    public ResponseEntity<List<Post>> findByAuthor(@RequestParam(defaultValue = "") String authorName) { //if the value has the same name of the parameter, you don't need to specify
        authorName = URL.decodeParam(authorName);
        List<Post> posts = service.findByAuthor(authorName);

        return ResponseEntity.ok().body(posts);
    }

    @GetMapping(value = "/textAndDateSearch") // posts/textAndDateSearch?text=proveite&minDate=2018-03-18&maxDate=2018-03-21
    public ResponseEntity<PostPageDTO> textAndDateSearch(
            @RequestParam(defaultValue = "") String text, 
            @RequestParam(defaultValue = "") String minDate,
            @RequestParam(defaultValue = "") String maxDate,
            @RequestParam(defaultValue = "0") int page, // Initial page (0)
            @RequestParam(defaultValue = "10") int size // Default of 10 posts
            ) {
        text = URL.decodeParam(text);
        LocalDate min = URL.convertDate(minDate, LocalDate.parse("1970-01-01")); //minimum date accepted by MongoDB
        LocalDate max = URL.convertDate(maxDate, LocalDate.now().plusYears(100));

        Page<Post> postsPage = service.textAndDateSearch(text, min, max, page, size);
        PostPageDTO response = new PostPageDTO(postsPage);

        return ResponseEntity.ok().body(response); 
    }
    

    // COMMENT METHODS

    @GetMapping(value = "/{id}/comments") 
    public ResponseEntity<List<CommentDTO>> findAllComments(@PathVariable String id) {
        List<CommentDTO> comments = service.findAllComments(id);

        return ResponseEntity.ok().body(comments);
    }

    @GetMapping("/comments/{id}")
    public ResponseEntity<CommentDTO> findCommentById(@PathVariable String id) {
        CommentDTO comment = service.findCommentById(id);

        return ResponseEntity.ok().body(comment);
    }
    
    @PostMapping(value = "/{id}/comments/{userId}") 
    public ResponseEntity<CommentDTO> insertComment(
            @PathVariable String id, 
            @Valid @RequestBody CommentUpdateInsertDTO commentDTO, 
            @PathVariable String userId
        ) {
        CommentDTO comment = modelMapper.map(commentDTO, CommentDTO.class);
        comment = service.insertComment(id, comment, userId);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(id)
            .toUri();

        return ResponseEntity.created(uri).body(comment);
    }

    @DeleteMapping(value = "/comments/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable String id) {
        service.deleteComment(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("comments/{id}")
    public ResponseEntity<Void> updateComment(
            @PathVariable String id, 
            @RequestBody CommentUpdateInsertDTO commentDTO
        ) {
        CommentDTO comment = modelMapper.map(commentDTO, CommentDTO.class);
        comment.setId(id);
        service.updateComment(comment);

        return ResponseEntity.noContent().build();
    }
}