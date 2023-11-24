package com.challenge_5.challenge_5.dto.response;

import java.util.UUID;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class MerchantDto {
    private UUID id;

    @NotEmpty()
    private String merchantName;

    @NotEmpty()
    private String merchantLocation;

    private Boolean isOpen;
}
