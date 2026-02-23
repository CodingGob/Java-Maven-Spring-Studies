package com.example.spring_order_project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.spring_order_project.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {}