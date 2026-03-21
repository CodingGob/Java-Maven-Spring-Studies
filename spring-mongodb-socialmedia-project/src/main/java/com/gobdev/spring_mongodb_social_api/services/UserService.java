package com.gobdev.spring_mongodb_social_api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.gobdev.spring_mongodb_social_api.domain.User;
import com.gobdev.spring_mongodb_social_api.repositories.UserRepository;
import com.gobdev.spring_mongodb_social_api.services.exceptions.ObjectNotFoundException;
import com.gobdev.spring_mongodb_social_api.services.exceptions.UniqueViolationException;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private BCryptPasswordEncoder encoder;


    public List<User> findAll() {
        return repository.findAll();
    }

    public User findById(String id) {
        Optional<User> user = repository.findById(id);
        return user.orElseThrow(() -> new ObjectNotFoundException("No user found with Id '" + id + "'"));
    }

    public User insert(User obj) {
        String email = obj.getEmail();
        User existingUser = repository.findByEmail(email);
        if (existingUser != null) {
            throw new UniqueViolationException("E-mail '" + email + "' already used by another user");
        }

        obj.setPassword(encoder.encode(obj.getPassword()));
        return repository.insert(obj);
    }
}