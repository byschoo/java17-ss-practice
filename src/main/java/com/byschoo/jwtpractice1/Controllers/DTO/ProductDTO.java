package com.byschoo.jwtpractice1.Controllers.DTO;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDTO {

    private Long id;

    @NotBlank(message = "The product name is required.")
    @Pattern(regexp = "^[A-Z0-9][A-Z0-9a-zÁÉÍÓÚÜÑáéíóúüñ-\\s]+$", message = "The product name can only contain Latin letters.")
    @Size(min = 2, max = 30, message = "The product name can only be between 2 and 30 characters.")
    private String productName;

    @NotBlank(message = "The product description is required.")
    @Size(min = 2, max = 255, message = "The product name can only be between 2 and 255 characters.")
    private String description;
    
    @NotBlank(message = "The product category is required.")
    @Pattern(regexp = "^[A-Z][a-zA-ZÁÉÍÓÚÜÑáéíóúüñ-]*$", message = "The product name can only contain Latin letters. The first letter must be capitalized.")
    @Size(min = 4, max = 30, message = "The product category can only be between 4 and 30 characters.")
    private String category;

    @NotNull(message = "The price of the product is required")
    @DecimalMin(value="0.01")
    private BigDecimal price;

}
