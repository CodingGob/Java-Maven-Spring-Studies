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
        checkExistingEmail(obj.getEmail(), null);
        obj.setPassword(encoder.encode(obj.getPassword()));

        return repository.insert(obj);
    }

    public void delete(String id) {
        findById(id);
        repository.deleteById(id);
    }

    public User update(User obj) {
        User entity = findById(obj.getId());
        checkExistingEmail(obj.getEmail(), obj.getId());
        updateData(entity, obj);
        
        return repository.save(entity);
    }


    private void updateData(User entity, User obj) {
        entity.setName(obj.getName());
        entity.setEmail(obj.getEmail());
    }

    private void checkExistingEmail(String email, String userId) {
        User existingUser = repository.findByEmail(email);

        if (existingUser != null && !existingUser.getId().equals(userId)) {
            throw new UniqueViolationException("E-mail '" + email + "' already used by another user");
        }
    }
}