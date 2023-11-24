package com.challenge_5.challenge_5.dto.response;

import java.util.UUID;

import com.challenge_5.challenge_5.entity.Merchant;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductDto {
    private UUID id;

    @NotNull
    private Merchant merchant;

    @NotEmpty
    private String productName;

    @NotNull
    private Double price;
}
