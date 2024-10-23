package com.shivam.microservices.product.service;

import com.shivam.microservices.product.dto.ProductRequest;
import com.shivam.microservices.product.dto.ProductResponse;
import com.shivam.microservices.product.model.Product;
import com.shivam.microservices.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    public ProductResponse createProduct(ProductRequest request) {
        Product product = Product.builder()
                .name(request.name())
                .description(request.description())
                .price(request.price())
                .build();
        Product savedProduct = productRepository.save(product);
        log.info("Product {} is saved", savedProduct.getId());
        return new ProductResponse(savedProduct.getId(), savedProduct.getName(), savedProduct.getDescription(), savedProduct.getPrice());
    }

    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(product -> new ProductResponse(product.getId(), product.getName(), product.getDescription(), product.getPrice()))
                .toList();
    }

    public ProductResponse getProduct(String id) {
        return productRepository.findById(id)
                .map(product -> new ProductResponse(product.getId(), product.getName(), product.getDescription(), product.getPrice()))
                .orElse(null);
    }
}
