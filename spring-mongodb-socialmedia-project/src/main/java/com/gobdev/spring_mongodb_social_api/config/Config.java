package com.gobdev.spring_mongodb_social_api.config;

import java.time.LocalDate;
import java.util.Arrays;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.gobdev.spring_mongodb_social_api.domain.Post;
import com.gobdev.spring_mongodb_social_api.domain.User;
import com.gobdev.spring_mongodb_social_api.dto.AuthorDTO;
import com.gobdev.spring_mongodb_social_api.dto.CommentDTO;
import com.gobdev.spring_mongodb_social_api.repositories.PostRepository;
import com.gobdev.spring_mongodb_social_api.repositories.UserRepository;

@Configuration
public class Config implements CommandLineRunner{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;


    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        
        // Optional setting: Ensures it only maps if the name is identical.
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        
        // Optional setting: Manual mapping of fields with different names.
        //modelMapper.createTypeMap(UserInsertDTO.class, User.class).addMapping(UserInsertDTO::getSenha, User::setPassword);
        
        return modelMapper;
    }


    @Override
    public void run(String... args) throws Exception {

        User maria = new User(
            null, 
            "Maria Brown", 
            "maria@gmail.com", 
            encoder.encode("123456")); 
        User alex = new User(
            null, 
            "Alex Green", 
            "alex@gmail.com", 
            encoder.encode("123456"));
        User bob = new User(
            null, 
            "Bob Grey", 
            "bob@gmail.com", 
            encoder.encode("Xjds239j%3"));

        userRepository.deleteAll();
        userRepository.saveAll(Arrays.asList(maria, alex, bob));


        Post p1 = new Post(
            null, 
            LocalDate.parse("2018-03-21"),
            null, 
            "Partiu viagem", 
            "Vou viajar para São Paulo. Abraços!", 
            new AuthorDTO(maria));

        Post p2 = new Post(
            null, 
            LocalDate.parse("2018-03-23"),
            null, 
            "Bom dia", 
            "Acordei feliz hoje!", 
            new AuthorDTO(maria));
        
        postRepository.deleteAll();
        postRepository.saveAll(Arrays.asList(p1, p2));

        maria.getPosts().addAll(Arrays.asList(p1, p2));
        userRepository.save(maria);


        CommentDTO c1 = new CommentDTO(
            "Boa viagem mano!", 
            LocalDate.parse("2018-03-21"), 
            new AuthorDTO(alex));

        CommentDTO c2 = new CommentDTO(
            "Aproveite!", 
            LocalDate.parse("2018-03-22"), 
            new AuthorDTO(bob));

        CommentDTO c3 = new CommentDTO(
            "Tenha um ótimo dia!", 
            LocalDate.parse("2018-03-23"), 
            new AuthorDTO(alex));

        p1.getComments().addAll(Arrays.asList(c1, c2));
        p2.getComments().add(c3);

        postRepository.saveAll(Arrays.asList(p1, p2));
    }
}