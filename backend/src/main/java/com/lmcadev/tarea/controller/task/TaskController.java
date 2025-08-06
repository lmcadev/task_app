package com.lmcadev.tarea.controller.task;

import com.lmcadev.tarea.DTO.task.TaskDTORequest;
import com.lmcadev.tarea.DTO.task.TaskDTOResponse;
import com.lmcadev.tarea.service.task.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Crear una nueva tarea", description = "Endpoint para crear una nueva tarea.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tarea creada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<TaskDTOResponse> createTask(@RequestBody @Valid TaskDTORequest TaskDTORequest) {
        TaskDTOResponse response = taskService.createTask(TaskDTORequest);
        return ResponseEntity.ok(response);
    }


    @GetMapping(value = "/{id}")
    @Operation(summary = "Obtener una tarea por ID", description = "Endpoint para obtener los detalles de una tarea utilizando su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tarea obtenida exitosamente"),
            @ApiResponse(responseCode = "400", description = "ID inválido"),
            @ApiResponse(responseCode = "404", description = "Tarea no encontrada"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<TaskDTOResponse> getTaskById(@PathVariable Long id) {
        TaskDTOResponse response = taskService.getTaskById(id);
        return ResponseEntity.ok(response);
    }


    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Eliminar una tarea por ID", description = "Endpoint para eliminar una tarea específica utilizando su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tarea eliminada exitosamente"),
            @ApiResponse(responseCode = "400", description = "ID inválido"),
            @ApiResponse(responseCode = "404", description = "Tarea no encontrada"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<TaskDTOResponse> deleteTaskById(@PathVariable Long id) {
        TaskDTOResponse response = taskService.deleteTaskById(id);
        return ResponseEntity.ok(response);
    }


    @PutMapping(value = "/{id}")
    @Operation(summary = "Actualizar una tarea por ID", description = "Endpoint para actualizar una tarea específica utilizando su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tarea actualizada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida"),
            @ApiResponse(responseCode = "404", description = "Tarea no encontrada"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })


    public ResponseEntity<TaskDTOResponse> updateTaskById(@PathVariable Long id, @RequestBody @Valid TaskDTORequest request) {
        TaskDTOResponse response = taskService.updateTaskById(id, request);
        return ResponseEntity.ok(response);
    }
}
