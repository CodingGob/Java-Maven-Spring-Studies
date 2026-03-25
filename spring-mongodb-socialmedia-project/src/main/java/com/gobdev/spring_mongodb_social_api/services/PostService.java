package com.gobdev.spring_mongodb_social_api.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gobdev.spring_mongodb_social_api.domain.Post;
import com.gobdev.spring_mongodb_social_api.domain.User;
import com.gobdev.spring_mongodb_social_api.dto.AuthorDTO;
import com.gobdev.spring_mongodb_social_api.dto.CommentDTO;
import com.gobdev.spring_mongodb_social_api.repositories.PostRepository;
import com.gobdev.spring_mongodb_social_api.services.exceptions.ObjectNotFoundException;

@Service
public class PostService {

    @Autowired
    private PostRepository repository;

    @Autowired
    private UserService userService;


    public List<Post> findAll() {
        return repository.findAll();
    }

    public Post findById(String id) {
        Optional<Post> post = repository.findById(id);

        return post.orElseThrow(() -> new ObjectNotFoundException("No post found with Id '" + id + "'"));
    }

    public Post insert(Post obj, String userId) {
        User user = userService.findById(userId);
        obj.setAuthor(new AuthorDTO(user));
        obj.setDate(LocalDate.now());
        repository.insert(obj);

        user.getPosts().add(obj);
        userService.saveUser(user);

        return obj;
    }

    public void delete(String id) {
        findById(id);
        repository.deleteById(id);
    }

    public Post update(Post obj) {
        Post entity = findById(obj.getId());
        updatePostData(entity, obj);
        
        return repository.save(entity);
    }

    public List<CommentDTO> findAllComments(String id) {
        Post post = findById(id);

        return post.getComments();
    }

    public CommentDTO insertComment(String id, CommentDTO comment, String userId) {
        Post post = findById(id);
        User user = userService.findById(userId);
        comment.setAuthor(new AuthorDTO(user));
        comment.setDate(LocalDate.now());

        post.getComments().add(comment);
        repository.save(post);

        return comment;
    }

    private void updatePostData(Post entity, Post obj) {
        entity.setUpdateDate(LocalDate.now());
        entity.setTitle(obj.getTitle());
        entity.setBody(obj.getBody());
    }
}