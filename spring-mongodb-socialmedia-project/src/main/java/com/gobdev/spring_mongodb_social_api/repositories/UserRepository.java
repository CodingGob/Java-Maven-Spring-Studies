package com.gobdev.spring_mongodb_social_api.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.gobdev.spring_mongodb_social_api.domain.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    User findByEmail(String email); // Custom query method to find a user by email
}