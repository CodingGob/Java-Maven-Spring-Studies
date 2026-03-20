package com.gobdev.spring_mongodb_social_api.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gobdev.spring_mongodb_social_api.domain.User;
import com.gobdev.spring_mongodb_social_api.dtos.UserDTO;
import com.gobdev.spring_mongodb_social_api.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @Autowired
    private UserService userService;

    @GetMapping 
    public ResponseEntity<List<UserDTO>> findAll() {
        List<User> users = userService.findAll();
        List<UserDTO> userDTOs = users.stream()
            .map(x -> new UserDTO(x))
            .collect(Collectors.toList());

        return ResponseEntity.ok().body(userDTOs);
    }

    @GetMapping(value = "/{id}") 
    public ResponseEntity<UserDTO> findById(@PathVariable String id) {
        User user = userService.findById(id);
        UserDTO userDTO = new UserDTO(user);

        return ResponseEntity.ok().body(userDTO);
    }
}