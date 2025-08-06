package com.lmcadev.tarea.controller.user;

import com.lmcadev.tarea.DTO.user.UserDTORequest;
import com.lmcadev.tarea.DTO.user.UserDTOResponse;
import com.lmcadev.tarea.service.user.UserService;
import jakarta.persistence.PostUpdate;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {


    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserDTOResponse> createUser(@RequestBody @Valid UserDTORequest userDTORequest){
        UserDTOResponse response = userService.createUser(userDTORequest);
        return ResponseEntity.ok(response);
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<UserDTOResponse> updateUserById(@RequestParam Long id, @RequestBody @Valid UserDTORequest request){
        UserDTOResponse response = userService.updateUserById(id, request);
        return ResponseEntity.ok(response);
    }

    //obtener todos los usuarios
    @GetMapping(value = "/all")
    public ResponseEntity<Iterable<UserDTOResponse>> getAllUsers() {
        Iterable<UserDTOResponse> response = userService.getAllUsers();
        return ResponseEntity.ok(response);

    }
}
