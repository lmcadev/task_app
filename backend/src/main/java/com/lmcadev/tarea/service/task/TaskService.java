package com.lmcadev.tarea.service.task;

import com.lmcadev.tarea.DTO.task.TaskDTORequest;
import com.lmcadev.tarea.DTO.task.TaskDTOResponse;
import com.lmcadev.tarea.model.task.Task;
import com.lmcadev.tarea.model.user.User;
import com.lmcadev.tarea.repository.task.TaskRepository;
import com.lmcadev.tarea.repository.user.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class TaskService {


    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public TaskService(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    public TaskDTOResponse createTask(TaskDTORequest request) {

        // Validación del título
        if (request.getTitle() == null || request.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be empty");
        }

        // Buscar usuario por ID
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + request.getUserId()));

        // Mapear DTO a entidad
        Task task = new Task();
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setUser(user);

        // Guardar tarea
        Task savedTask = taskRepository.save(task);

        // Mapear entidad a DTO de respuesta
        TaskDTOResponse response = new TaskDTOResponse();
        response.setId(savedTask.getId());
        response.setTitle(savedTask.getTitle());
        response.setDescription(savedTask.getDescription());
        response.setUserId(user.getId());

        return response;
    }


    public TaskDTOResponse getTaskById(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Tarea no encontrada con ID: " + id));
        TaskDTOResponse response = new TaskDTOResponse();
        response.setId(task.getId());
        response.setTitle(task.getTitle());
        response.setDescription(task.getDescription());

        return response;
    }

    public TaskDTOResponse deleteTaskById(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Tarea no encontrada con ID: " + id));
        taskRepository.deleteById(id);
        TaskDTOResponse response = new TaskDTOResponse();
        response.setId(task.getId());
        response.setTitle(task.getTitle());
        response.setDescription(task.getDescription());

        return response;
    }

    public TaskDTOResponse updateTaskById(Long id, TaskDTORequest request) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Tarea no encontrada con ID: " + id));
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        Task savedTask = taskRepository.save(task);
        TaskDTOResponse response = new TaskDTOResponse();
        response.setId(savedTask.getId());
        response.setTitle(savedTask.getTitle());
        response.setDescription(savedTask.getDescription());

        return response;
    }
}
