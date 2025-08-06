package com.lmcadev.tarea.DTO.task;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskDTORequest {

    private long id;

    @NotBlank
    @Size(min = 4, max = 20)
    private String title;

    @Size(min = 4, max = 250)
    private String description;

    private Long userId;


}
