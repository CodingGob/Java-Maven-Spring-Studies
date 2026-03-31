package com.gobdev.spring_mongodb_social_api.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.gobdev.spring_mongodb_social_api.domain.Post;


public interface PostRepository extends MongoRepository<Post, String> {
    
    // Custom query using @Query annotation to find a post by a comment's id
    @Query("{ 'comments.id': ?0 }") 
    Optional<Post> findPostByCommentId(String commentId); 
    
    // Custom query using @Query annotation to find posts by author's name (case-insensitive)
    @Query("{ 'author.name': { $regex: ?0, $options: 'i' } }") 
    // $regex: ?0 = search for posts where the author's name matches the provided parameter (0 is the 1st, 1 the 2nd...),
    // $options: 'i' = case-insensitive search
    List<Post> findPostByAuthor(String authorName);
    
    // Custom query using Spring Data method naming convention to find posts by title containing a certain text (case-insensitive)
    List<Post> findByTitleContainingIgnoreCase(String text); // @Query annotation for that would be: @Query("{ 'title': { $regex: ?0, $options: 'i' } }")

    // Custom query using @Query annotation to find posts that contain a certain text in the title, body or comments and were created between two dates
    @Query("{ $and: [ { $or: [ { 'title': { $regex: ?0, $options: 'i' } }, { 'body': { $regex: ?0, $options: 'i' } }, { 'comments.text': { $regex: ?0, $options: 'i' } } ] }, { 'date': { $gte: ?1 } }, { 'date': { $lte: ?2 } } ] }")
    Page<Post> textAndDateSearch(String text, LocalDate minDate, LocalDate maxDate, Pageable pageable);
}