package com.lmcadev.tarea.controller.task;

import com.lmcadev.tarea.DTO.task.TaskDTORequest;
import com.lmcadev.tarea.DTO.task.TaskDTOResponse;
import com.lmcadev.tarea.service.task.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/task")
public class TaskController {


    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<TaskDTOResponse> createTask(@RequestBody @Valid TaskDTORequest TaskDTORequest){
        TaskDTOResponse response = taskService.createTask(TaskDTORequest);
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TaskDTOResponse> getTaskById(@RequestParam Long id){
        TaskDTOResponse response = taskService.getTaskById(id);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<TaskDTOResponse> deleteTaskById(@RequestParam Long id){
        TaskDTOResponse response = taskService.deleteTaskById(id);
        return ResponseEntity.ok(response);
    }


    @PutMapping(value = "{id}")
    public ResponseEntity<TaskDTOResponse> updateTaskById(@RequestParam Long id, @RequestBody @Valid TaskDTORequest request){
        TaskDTOResponse response = taskService.updateTaskById(id, request);
        return ResponseEntity.ok(response);
    }
}
