package com.challenge_5.challenge_5.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.challenge_5.challenge_5.entity.OrderDetail;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, UUID> {

}
