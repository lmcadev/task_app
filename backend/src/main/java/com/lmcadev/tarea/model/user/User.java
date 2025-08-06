package com.lmcadev.tarea.model.user;

import com.lmcadev.tarea.model.ENUM.Role;
import com.lmcadev.tarea.model.task.Task;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@EqualsAndHashCode(exclude = "password")
@ToString(exclude = "password")
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String username;

    @NonNull
    @Column(nullable = false)
    @Size(min = 8)
    private String password;

    @NotNull
    @Email
    @Column(nullable = false, unique = true)
    private String email;

    @Size(max = 20)
    private String firstName;

    @Size(max = 20)
    private String lastName;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Task> tasks = new ArrayList<>();


}
