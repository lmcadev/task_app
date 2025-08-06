package com.lmcadev.tarea.DTO.task;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class TaskDTOResponse {
    private Long id;
    private String title;
    private String description;
    private Long userId;
}
