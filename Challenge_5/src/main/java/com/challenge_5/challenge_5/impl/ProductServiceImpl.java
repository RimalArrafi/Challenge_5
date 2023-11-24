package com.challenge_5.challenge_5.impl;

import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.challenge_5.challenge_5.dto.request.CreateProductDto;
import com.challenge_5.challenge_5.dto.request.UpdateProductDto;
import com.challenge_5.challenge_5.dto.response.ProductDto;
import com.challenge_5.challenge_5.entity.Merchant;
import com.challenge_5.challenge_5.entity.Product;
import com.challenge_5.challenge_5.exception.ApiException;
import com.challenge_5.challenge_5.repository.MerchantRepository;
import com.challenge_5.challenge_5.repository.ProductRepository;
import com.challenge_5.challenge_5.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    MerchantRepository merchantRepository;

    @Override
    public ProductDto createProduct(CreateProductDto request) throws ApiException {
        Optional<Merchant> merchantOnDb = merchantRepository.findById(request.getMerchantId());

        if (merchantOnDb.isEmpty())
            throw new ApiException(HttpStatus.NOT_FOUND,
                    "Merchant with id " + request.getMerchantId() + " is not found.");

        Product product = modelMapper.map(request, Product.class);
        product.setMerchant(merchantOnDb.get());
        ProductDto productDto = modelMapper.map(productRepository.save(product), ProductDto.class);

        return productDto;
    }

    @Override
    public ProductDto updateProduct(UUID productId, UpdateProductDto request) throws ApiException {
        Optional<Product> productOnDb = productRepository.findById(productId);

        if (productOnDb.isEmpty())
            throw new ApiException(HttpStatus.NOT_FOUND,
                    "Product with id " + productId + " is not found.");

        Product existedProduct = productOnDb.get();
        if (request.getMerchantId().isPresent()) {
            Optional<Merchant> merchantOnDb = merchantRepository.findById(request.getMerchantId().get());
            if (merchantOnDb.isEmpty())
                throw new ApiException(HttpStatus.NOT_FOUND,
                        "Merchant with id " + request.getMerchantId() + " is not found.");
            existedProduct.setMerchant(merchantOnDb.get());
        }
        if (request.getProductName().isPresent())
            existedProduct.setProductName(request.getProductName().get());
        if (request.getPrice().isPresent())
            existedProduct.setPrice(request.getPrice().get());

        ProductDto productDto = modelMapper.map(productRepository.save(existedProduct), ProductDto.class);
        return productDto;
    }

    @Override
    public ProductDto getProductById(UUID productId) throws ApiException {
        Optional<Product> productOnDb = productRepository.findById(productId);

        if (productOnDb.isEmpty())
            throw new ApiException(HttpStatus.NOT_FOUND, "Product with id " + productId + " is not found!");

        ProductDto productDto = modelMapper.map(productOnDb.get(), ProductDto.class);
        return productDto;
    }

    @Override
    public Page<ProductDto> getAllProducts(Specification<Product> filterQueries, Pageable paginationQueries) {
        Page<Product> products = productRepository.findAll(filterQueries, paginationQueries);
        Page<ProductDto> productsDto = products.map(productEntity -> modelMapper.map(productEntity, ProductDto.class));
        return productsDto;
    }

    @Override
    public ProductDto deleteProduct(UUID productId) throws ApiException {
        Optional<Product> productOnDb = productRepository.findById(productId);

        if (productOnDb.isEmpty())
            throw new ApiException(HttpStatus.NOT_FOUND, "Product with id " + productId + " is not found!");

        Product deletedProduct = productOnDb.get();
        productRepository.delete(deletedProduct);
        ProductDto productDto = modelMapper.map(deletedProduct, ProductDto.class);
        return productDto;
    }
}
