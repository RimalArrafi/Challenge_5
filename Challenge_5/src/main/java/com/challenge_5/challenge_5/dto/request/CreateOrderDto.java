package com.challenge_5.challenge_5.dto.request;

import java.util.List;
import java.util.UUID;

import com.challenge_5.challenge_5.dto.response.OrderDetailDto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateOrderDto {
    @NotEmpty
    private String destinationAddress;

    @NotNull
    private UUID userId;

    @NotNull
    private List<OrderDetailDto> orderDetails;
}
