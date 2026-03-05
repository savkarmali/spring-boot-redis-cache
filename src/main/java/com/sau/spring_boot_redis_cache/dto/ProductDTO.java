package com.sau.spring_boot_redis_cache.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProductDTO(
        Long id,
        @NotNull
        String name,
        @Positive
        BigDecimal price) {
}
