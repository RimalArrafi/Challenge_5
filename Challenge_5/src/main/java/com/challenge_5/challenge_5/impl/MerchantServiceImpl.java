package com.challenge_5.challenge_5.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.challenge_5.challenge_5.dto.request.CreateMerchantDto;
import com.challenge_5.challenge_5.dto.request.UpdateMerchantDto;
import com.challenge_5.challenge_5.dto.response.MerchantDto;
import com.challenge_5.challenge_5.entity.Merchant;
import com.challenge_5.challenge_5.exception.ApiException;
import com.challenge_5.challenge_5.repository.MerchantRepository;
import com.challenge_5.challenge_5.service.MerchantService;

@Service
public class MerchantServiceImpl implements MerchantService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MerchantRepository merchantRepository;

    @Override
    public MerchantDto createMerchant(CreateMerchantDto request) {
        Merchant merchant = modelMapper.map(request, Merchant.class);
        MerchantDto merchantDto = modelMapper.map(merchantRepository.save(merchant), MerchantDto.class);
        return merchantDto;
    }

    @Override
    public MerchantDto updateMerchant(UUID id, UpdateMerchantDto request) throws ApiException {
        Optional<Merchant> merchantOnDb = merchantRepository.findById(id);

        if (merchantOnDb.isEmpty())
            throw new ApiException(HttpStatus.NOT_FOUND, "Merchant with coresponding id is not found!");

        Merchant existedMerchant = merchantOnDb.get();
        if (request.getMerchantName().isPresent())
            existedMerchant.setMerchantName(request.getMerchantName().get());
        if (request.getMerchantLocation().isPresent())
            existedMerchant.setMerchantLocation(request.getMerchantLocation().get());
        if (request.getIsOpen().isPresent())
            existedMerchant.setIsOpen(request.getIsOpen().get());
        MerchantDto merchantDto = modelMapper.map(merchantRepository.save(existedMerchant), MerchantDto.class);

        return merchantDto;
    }

    @Override
    public List<MerchantDto> getAllMerchants(Specification<Merchant> filterQueries) {
        List<Merchant> merchants = merchantRepository.findAll(filterQueries);
        List<MerchantDto> merchantsDto = merchants.stream().map(m -> modelMapper.map(m, MerchantDto.class))
                .collect(Collectors.toList());
        return merchantsDto;
    }

    @Override
    public MerchantDto getMerchantById(UUID id) throws ApiException {
        Optional<Merchant> merchantOnDb = merchantRepository.findById(id);
        if (merchantOnDb.isEmpty())
            throw new ApiException(HttpStatus.NOT_FOUND, "Merchant with coresponding id is not found!");

        MerchantDto merchantDto = modelMapper.map(merchantOnDb.get(), MerchantDto.class);
        return merchantDto;
    }

    @Override
    public MerchantDto deleteMerchant(UUID merchantId) throws ApiException {
        Optional<Merchant> merchantOnDb = merchantRepository.findById(merchantId);

        if (merchantOnDb.isEmpty())
            throw new ApiException(HttpStatus.NOT_FOUND, "Merchant with id " + merchantId + " is not found!");

        Merchant deletedMerchant = merchantOnDb.get();
        merchantRepository.delete(deletedMerchant);
        MerchantDto merchantDto = modelMapper.map(deletedMerchant, MerchantDto.class);
        return merchantDto;
    }

}
