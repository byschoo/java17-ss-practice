package com.byschoo.jwtpractice1.Controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.byschoo.jwtpractice1.Controllers.DTO.ProductDTO;
import com.byschoo.jwtpractice1.Controllers.DTO.Response.Success;
import com.byschoo.jwtpractice1.Services.Product.IProductService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {

    private final IProductService productService;
    @PreAuthorize("hasAnyRole('SUPER','ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<?> getAllProducts() {
    log.info("\"CONTROLLER LAYER - ProductController FROM getAllProducts\"");

        List<ProductDTO> productsFound = productService.findAllProducts();

        if (productsFound.isEmpty()) {
            Map<String, String> emptyMess = new HashMap<>();
            emptyMess.put("message", "The product list is empty.");

            return new ResponseEntity<>(
                Success.builder()
                    .object(emptyMess)
                    .build(),
                HttpStatus.OK
            );            
        }

        return new ResponseEntity<>(
            Success.builder()
                .message("All Products found:")
                .object(productsFound)
                .build(),
            HttpStatus.OK
        );
    }


    @PostMapping("/save")
    public ResponseEntity<Success> saveProduct(@RequestBody @Valid ProductDTO productDTO) {
    log.info("\"CONTROLLER LAYER - ProductController FROM saveProduct\"");

        return new ResponseEntity<>(
            Success.builder()
                .message("Product saved successfully.")
                .object(productService.saveProduct(productDTO))
                .build(),
            HttpStatus.CREATED
        );
    }

}
