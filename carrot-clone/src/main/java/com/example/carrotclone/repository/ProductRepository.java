package com.example.carrotclone.repository;

import com.example.carrotclone.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
