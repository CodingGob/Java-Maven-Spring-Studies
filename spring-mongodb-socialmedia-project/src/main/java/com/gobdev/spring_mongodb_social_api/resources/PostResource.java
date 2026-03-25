package com.gobdev.spring_mongodb_social_api.resources;

import java.net.URI;
import java.util.List;

import javax.xml.stream.events.Comment;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gobdev.spring_mongodb_social_api.domain.Post;
import com.gobdev.spring_mongodb_social_api.dto.CommentDTO;
import com.gobdev.spring_mongodb_social_api.dto.CommentUpdateInsertDTO;
import com.gobdev.spring_mongodb_social_api.dto.PostUpdateInsertDTO;
import com.gobdev.spring_mongodb_social_api.services.PostService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {

    @Autowired
    private PostService service;

    @Autowired
    private ModelMapper modelMapper;


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
    public ResponseEntity<Post> insert(@PathVariable String userId, @RequestBody PostUpdateInsertDTO objDTO) {
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
    public ResponseEntity<Post> update(@PathVariable String id, @RequestBody PostUpdateInsertDTO objDTO) {
        Post post = modelMapper.map(objDTO, Post.class);
        post.setId(id); // Ensure the ID is set for the update operation
        service.update(post);

        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{id}/comments") 
    public ResponseEntity<List<CommentDTO>> findAllComments(@PathVariable String id) {
        List<CommentDTO> comments = service.findAllComments(id);

        return ResponseEntity.ok().body(comments);
    }

    @PostMapping(value = "/{id}/comments/{userId}") 
    public ResponseEntity<CommentDTO> insertComment(
        @PathVariable String id, 
        @RequestBody CommentUpdateInsertDTO commentDTO, 
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
}