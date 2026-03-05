package com.sau.spring_boot_redis_cache.dao;

import com.sau.spring_boot_redis_cache.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
