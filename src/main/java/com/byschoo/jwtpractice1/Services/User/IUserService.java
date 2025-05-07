package com.byschoo.jwtpractice1.Services.User;

import java.util.List;

import com.byschoo.jwtpractice1.Controllers.DTO.UserDTO;

public interface IUserService {

    List<UserDTO> findAllUsers();
    UserDTO findUserById(Long id);
    UserDTO findUserByUsername(String username);
    UserDTO findUserByEmail(String email);
    UserDTO saveUser(UserDTO userDTO);
    List<UserDTO> saveAllUsers(List<UserDTO> listUserDTO);
}
