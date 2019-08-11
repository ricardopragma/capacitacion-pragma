package com.familia.api.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.familia.api.core.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
