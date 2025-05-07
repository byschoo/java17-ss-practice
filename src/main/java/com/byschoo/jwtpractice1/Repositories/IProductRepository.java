package com.byschoo.jwtpractice1.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.byschoo.jwtpractice1.Entities.Product;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long>{

}
