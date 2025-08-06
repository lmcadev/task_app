package com.lmcadev.tarea.DTO.user;

import com.lmcadev.tarea.model.ENUM.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTOResponse {
    private Long id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private Role rol;
}
