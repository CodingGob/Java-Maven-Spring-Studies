package com.gobdev.spring_mongodb_social_api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gobdev.spring_mongodb_social_api.domain.User;
import com.gobdev.spring_mongodb_social_api.repositories.UserRepository;
import com.gobdev.spring_mongodb_social_api.services.exceptions.ObjectNotFoundException;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(String id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(() -> new ObjectNotFoundException(id));
    }
}
