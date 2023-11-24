package com.challenge_5.challenge_5.service;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.challenge_5.challenge_5.dto.request.CreateProductDto;
import com.challenge_5.challenge_5.dto.request.UpdateProductDto;
import com.challenge_5.challenge_5.dto.response.ProductDto;
import com.challenge_5.challenge_5.entity.Product;
import com.challenge_5.challenge_5.exception.ApiException;

public interface ProductService {
    public ProductDto createProduct(CreateProductDto request) throws ApiException;

    public ProductDto updateProduct(UUID productId, UpdateProductDto request) throws ApiException;

    public ProductDto getProductById(UUID productId) throws ApiException;

    public ProductDto deleteProduct(UUID productId) throws ApiException;;

    public Page<ProductDto> getAllProducts(Specification<Product> filterQueries, Pageable paginationQueries);

}
