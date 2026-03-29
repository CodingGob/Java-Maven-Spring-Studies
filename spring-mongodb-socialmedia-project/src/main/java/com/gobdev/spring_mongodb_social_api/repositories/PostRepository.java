package com.gobdev.spring_mongodb_social_api.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.gobdev.spring_mongodb_social_api.domain.Post;


public interface PostRepository extends MongoRepository<Post, String> {
    
    @Query("{ 'comments.id': ?0 }") 
    Optional<Post> findPostByCommentId(String commentId); // Custom query using @Query annotation
    
    @Query("{ 'author.name': { $regex: ?0, $options: 'i' } }") 
    // $regex: ?0 = search for posts where the author's name matches the provided parameter,
    // $options: 'i' = case-insensitive search
    List<Post> findPostByAuthor(String authorName);
    
    List<Post> findByTitleContainingIgnoreCase(String text); // Custom query using Spring Data method naming convention
    // @Query annotation for that would be: @Query("{ 'title': { $regex: ?0, $options: 'i' } }")
}