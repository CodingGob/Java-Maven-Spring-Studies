package com.gobdev.spring_mongodb_social_api.config;

import java.util.Arrays;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.gobdev.spring_mongodb_social_api.domain.User;
import com.gobdev.spring_mongodb_social_api.repositories.UserRepository;

@Configuration
public class Config implements CommandLineRunner{

    @Autowired
    private UserRepository userRepository;


    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Override
    public void run(String... args) throws Exception {
        User maria = new User(null, "Maria Brown", "maria@gmail.com"); 
        User alex = new User(null, "Alex Green", "alex@gmail.com"); 
        User bob = new User(null, "Bob Grey", "bob@gmail.com");

        userRepository.deleteAll();
        userRepository.saveAll(Arrays.asList(maria, alex, bob));
    }
}