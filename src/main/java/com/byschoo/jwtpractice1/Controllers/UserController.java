package com.byschoo.jwtpractice1.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.byschoo.jwtpractice1.Controllers.DTO.UserDTO;
import com.byschoo.jwtpractice1.Controllers.DTO.Response.Success;
import com.byschoo.jwtpractice1.Services.User.IUserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final IUserService userService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllUsers() {
    log.info("\"CONTROLLER LAYER - UserController FROM getAllUsers\"");

        List<UserDTO> usersFound = userService.findAllUsers();

        if (usersFound.isEmpty()) {
            Map<String, String> emptyMess = new HashMap<>();
            emptyMess.put("message", "The user list is empty.");

            return new ResponseEntity<>(
                Success.builder()
                    .object(emptyMess)
                    .build(),
                HttpStatus.OK
            );            
        }

        return new ResponseEntity<>(
            Success.builder()
                .message("All Users found:")
                .object(usersFound)
                .build(),
            HttpStatus.OK
        );
    }


    @GetMapping("/byid/{id}")
    public ResponseEntity<Success> getUserById(@PathVariable Long id) {
    log.info("\"CONTROLLER LAYER - UserController FROM getUserById\"");

        return new ResponseEntity<>(
            Success.builder()
                .message("User found by Id:")
                .object(userService.findUserById(id))
                .build(),
            HttpStatus.OK);
    }
    

    @GetMapping("/byusername/{username}")
    public ResponseEntity<Success> getUserByUsername(@PathVariable String username) {
    log.info("\"CONTROLLER LAYER - UserController FROM getUserByUsername\"");

        return new ResponseEntity<>(
            Success.builder()
                .message("User found by username:")
                .object(userService.findUserByUsername(username))
                .build(),
            HttpStatus.OK);
    }
    

    @GetMapping("/byemail/{email}")
    public ResponseEntity<Success> getUserByEmail(@PathVariable String email) {
    log.info("\"CONTROLLER LAYER - UserController FROM getUserByEmail\"");

        return new ResponseEntity<>(
            Success.builder()
                .message("User found by email:")
                .object(userService.findUserByEmail(email))
                .build(),
            HttpStatus.OK);
    }
    

    @PostMapping("/save")
    public ResponseEntity<Success> saveUser(@RequestBody @Valid UserDTO userDTO) {
    log.info("\"CONTROLLER LAYER - UserController FROM saveUser\"");

        return new ResponseEntity<>(
            Success.builder()
                .message("The user has been saved.")
                .object(userService.saveUser(userDTO))
                .build(),
            HttpStatus.CREATED);
    }

    
    @PostMapping("/saveall")
    public ResponseEntity<Success> saveAllUser(@RequestBody @Valid List<UserDTO> userDTO) {
    log.info("\"CONTROLLER LAYER - UserController FROM saveAllUser\"");

        return new ResponseEntity<>(
            Success.builder()
                .message("The users has been saved.")
                .object(userService.saveAllUsers(userDTO))
                .build(),
            HttpStatus.CREATED);
    }

}
