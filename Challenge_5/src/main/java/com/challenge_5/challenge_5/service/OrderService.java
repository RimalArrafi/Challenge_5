package com.challenge_5.challenge_5.service;

import java.util.List;
import java.util.UUID;

import com.challenge_5.challenge_5.dto.OrderDto;
import com.challenge_5.challenge_5.dto.request.CreateOrderDto;
import com.challenge_5.challenge_5.exception.ApiException;

public interface OrderService {
    public OrderDto createOrder(CreateOrderDto request) throws ApiException;

    public List<OrderDto> getAllOrders();

    public List<OrderDto> getOrdersByUserId(UUID userId) throws ApiException;

    public OrderDto getOrderById(UUID orderId) throws ApiException;

    public OrderDto deleteOrder(UUID orderId) throws ApiException;
}
