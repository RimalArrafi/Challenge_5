package com.challenge_5.challenge_5.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.challenge_5.challenge_5.dto.OrderDto;
import com.challenge_5.challenge_5.dto.request.CreateOrderDto;
import com.challenge_5.challenge_5.exception.ApiException;
import com.challenge_5.challenge_5.service.OrderService;
import com.challenge_5.challenge_5.utils.ResponseHandler;

import jakarta.validation.Valid;

@Controller

@RequestMapping("/v1/orders")
public class OrderController {
    @Autowired
    OrderService orderService;

    @PostMapping
    public ResponseEntity<Object> createOrder(@Valid @RequestBody CreateOrderDto request) {
        try {
            OrderDto newOrder = orderService.createOrder(request);
            return ResponseHandler.generateResponseSuccess(HttpStatus.OK,
                    "Merchant has successfully created!", newOrder);
        } catch (Exception e) {
            return ResponseHandler.generateResponseFailed(
                    HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Object> deleteOrder(@PathVariable UUID orderId) {
        try {
            OrderDto order = orderService.deleteOrder(orderId);
            return ResponseHandler.generateResponseSuccess(HttpStatus.OK,
                    "Merchant has successfully deleted!", order);
        } catch (Exception e) {
            return ResponseHandler.generateResponseFailed(
                    HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }


    @GetMapping
    public ResponseEntity<Object> getAllOrders() {
        try {
            List<OrderDto> orders = orderService.getAllOrders();
            return ResponseHandler.generateResponseSuccess(HttpStatus.OK,
                    "Merchant has successfully fetched!", orders);
        } catch (Exception e) {
            return ResponseHandler.generateResponseFailed(
                    HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Object> getOrderById(@PathVariable UUID orderId) {
        try {
            OrderDto order = orderService.getOrderById(orderId);
            return ResponseHandler.generateResponseSuccess(HttpStatus.OK,
                    "Merchant has successfully fetched!", order);
        } catch (Exception e) {
            return ResponseHandler.generateResponseFailed(
                    HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @GetMapping("users/{userId}")
    public ResponseEntity<Object> getOrdersByUserId(@PathVariable UUID userId) {
        try {
            List<OrderDto> userOrders = orderService.getOrdersByUserId(userId);
            return ResponseHandler.generateResponseSuccess(HttpStatus.OK,
                    "User orders has successfully fetched!", userOrders);
        } catch (ApiException e) {
            return ResponseHandler.generateResponseFailed(
                    e.getStatus(), e.getMessage());
        } catch (Exception e) {
            return ResponseHandler.generateResponseFailed(
                    HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

}
