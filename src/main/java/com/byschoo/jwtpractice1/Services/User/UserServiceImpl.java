package com.byschoo.jwtpractice1.Services.User;

import java.util.List;
import java.util.stream.Collectors;

import com.byschoo.jwtpractice1.Entities.User;
import com.byschoo.jwtpractice1.Exceptions.Custom.ResourceNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.byschoo.jwtpractice1.Controllers.DTO.UserDTO;
import com.byschoo.jwtpractice1.Repositories.IUserRepository;
import com.byschoo.jwtpractice1.Services.Mappers.AppMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AppMapper appMapper;


    @Override
    @Transactional(readOnly = true)
    public List<UserDTO> findAllUsers() {
    log.info("\"SERVICE LAYER - UserServiceImpl FROM findAllUsers\"");

        List<User> usersFound = userRepository.findAll();

        return usersFound.stream()
            .map(appMapper::convertToUserDTO)
            .collect(Collectors.toList());                                        
    }

    @Override
    @Transactional(readOnly = true)
    public UserDTO findUserById(Long id) {        
    log.info("\"SERVICE LAYER - UserServiceImpl FROM findUserById\"");

        return userRepository.findUserById(id)
            .map(appMapper::convertToUserDTO)
            .orElseThrow(() -> new ResourceNotFoundException("User not found with Id: " + id, HttpStatus.NOT_FOUND)
        );
    }

    @Override
    @Transactional(readOnly = true)
    public UserDTO findUserByUsername(String username) {
    log.info("\"SERVICE LAYER - UserServiceImpl FROM findUserByUsername\"");

        return userRepository.findUserByUsername(username)
            .map(appMapper::convertToUserDTO)
            .orElseThrow(() -> new ResourceNotFoundException("User not found with username: " + username, HttpStatus.NOT_FOUND)
        );
    }

    @Override
    @Transactional(readOnly = true)
    public UserDTO findUserByEmail(String email) {
    log.info("\"SERVICE LAYER - UserServiceImpl FROM findUserByEmail\"");

        return userRepository.findUserByEmail(email)
            .map(appMapper::convertToUserDTO)
            .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + email, HttpStatus.NOT_FOUND)
        );
    }

    @Override
    @Transactional
    public UserDTO saveUser(UserDTO userDTO) {
    log.info("\"SERVICE LAYER - UserServiceImpl FROM saveUser\"");

        checkUserAlreadyExists(userDTO);

        User userEntity = appMapper.convertToUserEntity(userDTO);
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        User userSaved = userRepository.save(userEntity);
        return appMapper.convertToUserDTO(userSaved);
    }

    @Override
    @Transactional
    public List<UserDTO> saveAllUsers(List<UserDTO> listUserDTO) {
    log.info("\"SERVICE LAYER - UserServiceImpl FROM saveAllUsers\"");

        List<User> usersEntity = listUserDTO.stream()
            .map(this::checkUserAlreadyExists)
            .map(appMapper::convertToUserEntity)
            .collect(Collectors.toList());
        
        List<User> usersSaved = userRepository.saveAll(usersEntity);
        
        return usersSaved.stream()
            .map(appMapper::convertToUserDTO)
            .collect(Collectors.toList());
    }


    private UserDTO checkUserAlreadyExists(UserDTO userDTO) {
    log.info("\"SERVICE LAYER - UserServiceImpl FROM checkUserAlreadyExists\"");

        String message = String.format("The username '%s' or email '%s' already exists.", userDTO.getUsername(), userDTO.getEmail());

        if(userRepository.existsByEmail(userDTO.getEmail()) || userRepository.existsByUsername(userDTO.getUsername())) {
            throw new ResourceNotFoundException(message , HttpStatus.BAD_REQUEST);
        }

        return userDTO;
    }

}
