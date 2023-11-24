package com.challenge_5.challenge_5.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.challenge_5.challenge_5.entity.Order;

public interface OrderRepository extends JpaRepository<Order, UUID> {
    List<Order> findAllByUserId(UUID userId);
}
