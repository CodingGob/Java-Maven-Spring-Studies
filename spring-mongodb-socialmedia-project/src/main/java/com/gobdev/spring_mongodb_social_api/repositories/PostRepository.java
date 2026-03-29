package com.gobdev.spring_mongodb_social_api.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.gobdev.spring_mongodb_social_api.domain.Post;


public interface PostRepository extends MongoRepository<Post, String> {
    @Query("{ 'comments.id': ?0 }")
    Optional<Post> findPostByCommentId(String commentId);
}