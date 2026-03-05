package com.sau.spring_boot_redis_cache.controller;

import com.sau.spring_boot_redis_cache.dto.ProductDTO;
import com.sau.spring_boot_redis_cache.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public ProductDTO createProduct(@RequestBody @Valid ProductDTO productDTO) {
        return productService.createProduct(productDTO);
    }

    @GetMapping("/{productId}")
    @ResponseStatus(value = HttpStatus.OK)
    public ProductDTO getProductById(@PathVariable Long productId) {
        return productService.getProductById(productId);
    }

    @PutMapping
    @ResponseStatus(value = HttpStatus.OK)
    public ProductDTO updateProduct(@RequestBody @Valid ProductDTO productDTO) {
        return productService.updateProduct(productDTO);
    }

    @DeleteMapping("/{productId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable Long productId) {
         productService.deleteProduct(productId);
    }
}
