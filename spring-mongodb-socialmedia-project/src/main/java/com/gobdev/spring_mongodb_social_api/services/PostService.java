package com.gobdev.spring_mongodb_social_api.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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


    // POST METHODS

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

    public List<Post> findByTitle(String text) {
        return repository.findByTitleContainingIgnoreCase(text);
    }

    public List<Post> findByAuthor(String authorName) {
        return repository.findPostByAuthor(authorName);
    }

    public List<Post> textAndDateSearch(String text, LocalDate minDate, LocalDate maxDate) {
        return repository.textAndDateSearch(text, minDate, maxDate);
    }


    // COMMENT METHODS

    public CommentDTO findCommentById(String commentId) {
        Post post = findPostByCommentId(commentId);

        return post.getComments().stream()
            .filter(c -> c.getId().equals(commentId))
            .findFirst()
            .orElseThrow(() -> new ObjectNotFoundException("Comment inconsistency in post"));
    }

    public List<CommentDTO> findAllComments(String postId) {
        Post post = findById(postId);

        return post.getComments();
    }

    public CommentDTO insertComment(String commentId, CommentDTO commentObj, String userId) {
        Post post = findById(commentId);
        User user = userService.findById(userId);

        commentObj.setId(UUID.randomUUID().toString());
        commentObj.setDate(LocalDate.now());
        commentObj.setAuthor(new AuthorDTO(user));

        post.getComments().add(commentObj);
        repository.save(post);

        return commentObj;
    }

    public void deleteComment(String commentId) {
        Post post = findPostByCommentId(commentId);

        boolean removed = post.getComments().removeIf(c -> c.getId().equals(commentId));

        if (!removed) {
            throw new ObjectNotFoundException("Comment inconsistency in post");
        }

        repository.save(post);
    }

    public CommentDTO updateComment(CommentDTO commentObj) {
        Post post = findPostByCommentId(commentObj.getId());
        
        CommentDTO entity = post.getComments().stream()
            .filter(c -> c.getId().equals(commentObj.getId()))
            .findFirst()
            .orElseThrow(() -> new ObjectNotFoundException("Comment inconsistency in post"));

        updateCommentData(entity, commentObj);

        repository.save(post);
        return entity;
    }


    // SUPPORT METHODS

    private void updatePostData(Post entity, Post obj) {
        entity.setUpdateDate(LocalDate.now());
        entity.setTitle(obj.getTitle());
        entity.setBody(obj.getBody());
    }

    private Post findPostByCommentId(String commentId) {
        Optional<Post> post = repository.findPostByCommentId(commentId);
        return post.orElseThrow(() -> new ObjectNotFoundException("No comment found with Id '" + commentId + "'"));
    }

    private void updateCommentData(CommentDTO entity, CommentDTO obj) {
        entity.setUpdateDate(LocalDate.now());
        entity.setText(obj.getText());
    }
}