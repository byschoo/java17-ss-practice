package com.byschoo.jwtpractice1.Controllers.DTO;

import com.byschoo.jwtpractice1.Utils.Role;

import jakarta.validation.constraints.Email;
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
public class UserDTO {

    private Long id;
    
    @NotBlank(message = "The first name is required.")
    @Pattern(regexp = "^[A-Za-zÁÉÍÓÚÜÑáéíóúüñ][A-Za-zÁÉÍÓÚÑáéíóúñ'\\s]+$", message = "The first name must begin with and contain only letters from the English or Latin alphabet.")
    @Size(min = 2, message = "First name must be at least 2 characters long.")
    private String firstName;
    
    @NotBlank(message = "The last name is required.")
    @Pattern(regexp = "^[A-Za-zÁÉÍÓÚÜÑáéíóúüñ][A-Za-zÁÉÍÓÚÑáéíóúñ'\\s]+$", message = "The last name must begin with and contain only letters from the English or Latin alphabet.")
    @Size(min = 2, message = "Last name must be at least 2 characters long.")
    private String lastName;
    
    @NotBlank(message = "The eMail is required.")
    @Email(message = "Invalid eMail format.")
    private String email;
    
    @NotBlank(message = "The username is required.")
    @Pattern(regexp = "^[A-Za-zÁÉÍÓÚÜÑáéíóúüñ][A-Za-zÁÉÍÓÚÑáéíóúñ'\\s]+$", message = "The username must begin with and contain only letters from the English or Latin alphabet.")
    @Size(min = 6, max = 12, message = "Invalid Username format. Must be between 6 and 12 characters.")
    private String username;
    
    @NotBlank(message = "The password is required.")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+-*/=])(?=\\S+$).*", message = "Password must contain at least one digit, one lowercase, one uppercase, and one special character @#$%^&+-*/=") // Ejemplo de regex de complejidad (puede variar)
    @Size(min = 8, message = "Password must be at least 8 characters long.")
    private String password;
    
    @NotNull(message = "The role is required. Check the names of the allowed roles.")
    private Role role;
    
}
