package com.byschoo.jwtpractice1.Services.Product;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.byschoo.jwtpractice1.Controllers.DTO.ProductDTO;
import com.byschoo.jwtpractice1.Entities.Product;
import com.byschoo.jwtpractice1.Repositories.IProductRepository;
import com.byschoo.jwtpractice1.Services.Mappers.AppMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements IProductService{

    private final IProductRepository productRepository;
    private final AppMapper appMapper;


    @Override
    public List<ProductDTO> findAllProducts() {
    log.info("\"SERVICE LAYER - ProductServiceImpl FROM findAllProducts\"");

        List<ProductDTO> productsDTO = productRepository.findAll().stream()
                                            .map(appMapper::convertToProductDTO) // Con lambda .map(product -> convertToDTO(product))
                                            .collect(Collectors.toList());
        return productsDTO;
    }


    @Override
    public ProductDTO saveProduct(ProductDTO productDTO) {
    log.info("\"SERVICE LAYER - ProductServiceImpl FROM saveProduct\"");

        Product product = appMapper.convertToProductEntity(productDTO);
        Product savedProduct = productRepository.save(product);
        return appMapper.convertToProductDTO(savedProduct);
    }

}
