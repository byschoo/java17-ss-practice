package com.byschoo.jwtpractice1.Services.Product;

import java.util.List;

import com.byschoo.jwtpractice1.Controllers.DTO.ProductDTO;

public interface IProductService {

    List<ProductDTO> findAllProducts();
    ProductDTO saveProduct(ProductDTO productDTO);

}
