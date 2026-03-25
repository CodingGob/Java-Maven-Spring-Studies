package com.gobdev.spring_mongodb_social_api.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.gobdev.spring_mongodb_social_api.domain.Post;

public interface PostRepository extends MongoRepository<Post, String> {}
