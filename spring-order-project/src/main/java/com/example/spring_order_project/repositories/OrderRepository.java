package com.example.spring_order_project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.spring_order_project.entities.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {}