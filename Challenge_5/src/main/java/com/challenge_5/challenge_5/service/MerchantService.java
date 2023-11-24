package com.challenge_5.challenge_5.service;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.domain.Specification;

import com.challenge_5.challenge_5.dto.request.CreateMerchantDto;
import com.challenge_5.challenge_5.dto.request.UpdateMerchantDto;
import com.challenge_5.challenge_5.dto.response.MerchantDto;
import com.challenge_5.challenge_5.entity.Merchant;
import com.challenge_5.challenge_5.exception.ApiException;

public interface MerchantService {
    public MerchantDto createMerchant(CreateMerchantDto request);

    public MerchantDto updateMerchant(UUID merchantId, UpdateMerchantDto request) throws ApiException;

    public MerchantDto getMerchantById(UUID merchantId) throws ApiException;

    public MerchantDto deleteMerchant(UUID merchantId) throws ApiException;;

    public List<MerchantDto> getAllMerchants(Specification<Merchant> filterQueries);
}
