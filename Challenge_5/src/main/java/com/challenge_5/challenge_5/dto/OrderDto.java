package com.challenge_5.challenge_5.dto;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.challenge_5.challenge_5.dto.response.OrderDetailDto;
import com.challenge_5.challenge_5.dto.response.UserDto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderDto {
    @NotNull
    private UUID id;

    @NotEmpty
    private String destinationAddress;

    @NotNull
    private UserDto user;

    @NotNull
    private List<OrderDetailDto> orderDetails;

    @NotNull
    private Double finalPrice;

    @NotNull
    private Date orderTime;
}
