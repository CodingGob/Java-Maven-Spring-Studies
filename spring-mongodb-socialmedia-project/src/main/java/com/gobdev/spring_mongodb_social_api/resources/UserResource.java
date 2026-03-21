package com.gobdev.spring_mongodb_social_api.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gobdev.spring_mongodb_social_api.domain.User;
import com.gobdev.spring_mongodb_social_api.dto.UserDTO;
import com.gobdev.spring_mongodb_social_api.dto.UserInsertDTO;
import com.gobdev.spring_mongodb_social_api.services.UserService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @Autowired
    private UserService service;

    @Autowired
    private ModelMapper modelMapper;


    @GetMapping 
    public ResponseEntity<List<UserDTO>> findAll() {
        List<User> users = service.findAll();
        List<UserDTO> userDTOs = users.stream()
            .map(x -> modelMapper.map(x, UserDTO.class))
            .collect(Collectors.toList());

        return ResponseEntity.ok().body(userDTOs);
    }

    @GetMapping(value = "/{id}") 
    public ResponseEntity<UserDTO> findById(@PathVariable String id) {
        User user = service.findById(id);
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);

        return ResponseEntity.ok().body(userDTO);
    }

    @PostMapping
    public ResponseEntity<UserDTO> insert(@RequestBody UserInsertDTO objDto) {
        User user = modelMapper.map(objDto, User.class);
        user = service.insert(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(user.getId())
            .toUri();

        UserDTO userDTO = modelMapper.map(user, UserDTO.class);

        return ResponseEntity.created(uri).body(userDTO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> delete(@PathVariable String id, @RequestBody UserDTO objDto) {
        User user = modelMapper.map(objDto, User.class);
        user.setId(id); // Ensure the ID is set for the update operation
        service.update(user);

        return ResponseEntity.noContent().build();
    }
}