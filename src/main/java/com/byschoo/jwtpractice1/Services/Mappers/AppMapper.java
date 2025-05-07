package com.byschoo.jwtpractice1.Services.Mappers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.byschoo.jwtpractice1.Controllers.DTO.ProductDTO;
import com.byschoo.jwtpractice1.Controllers.DTO.UserDTO;
import com.byschoo.jwtpractice1.Entities.Product;
import com.byschoo.jwtpractice1.Entities.User;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AppMapper {

    private final ModelMapper modelMapper;

    public User convertToUserEntity(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }

    public UserDTO convertToUserDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    public Product convertToProductEntity(ProductDTO productDTO) {
        return modelMapper.map(productDTO, Product.class);
    }

    public ProductDTO convertToProductDTO(Product product) {
        return modelMapper.map(product, ProductDTO.class);
    }

}
