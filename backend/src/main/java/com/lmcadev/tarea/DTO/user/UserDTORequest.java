package com.lmcadev.tarea.DTO.user;

import com.lmcadev.tarea.model.ENUM.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTORequest {

    private long id;

    @NotBlank
    @Size(min = 4, max = 20)
    private String username;

    @NotBlank
    @Size(min = 6)
    private String password;

    @Email
    @NotBlank
    private String email;

    private String firstName;

    private String lastName;

    @NotBlank
    private Role role;
}
