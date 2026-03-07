package com.sau.spring_boot_redis_cache.service;

import com.sau.spring_boot_redis_cache.dao.ProductRepository;
import com.sau.spring_boot_redis_cache.dto.ProductDTO;
import com.sau.spring_boot_redis_cache.entity.Product;
import jakarta.validation.Valid;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    public static final String PRODUCT_CACHE = "products";
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @CachePut(value = PRODUCT_CACHE, key = "#result.id")
    public ProductDTO createProduct(ProductDTO productDTO) {
        var product = new Product();
        product.setName(productDTO.name());
        product.setPrice(productDTO.price());

        Product savedProduct = productRepository.save(product);
        return new ProductDTO(savedProduct.getId(),
                savedProduct.getName(),
                savedProduct.getPrice());
    }

    @Cacheable(value = PRODUCT_CACHE, key = "#productId")
    public ProductDTO getProductById(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found with id: " + productId));
        return new ProductDTO(product.getId(),
                product.getName(),
                product.getPrice());
    }

    @CachePut(value = PRODUCT_CACHE, key = "#productDTO.id")
    public ProductDTO updateProduct(@Valid ProductDTO productDTO) {
        Long productId = productDTO.id();
        Product existingProduct = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found with id: " + productId));

        existingProduct.setName(productDTO.name());
        existingProduct.setPrice(productDTO.price());

        Product updatedProduct = productRepository.save(existingProduct);
        return new ProductDTO(updatedProduct.getId(),
                updatedProduct.getName(),
                updatedProduct.getPrice());
    }

    @CacheEvict(value = PRODUCT_CACHE, key = "#productId")
    public void deleteProduct(Long productId) {
        Optional<Product> product = productRepository.findById(productId);
    }
}
