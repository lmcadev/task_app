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


    /**
     * Obtiene una tarea por su ID.
     *
     * @param id ID de la tarea a obtener.
     * @return TaskDTOResponse con los detalles de la tarea encontrada.
     * @throws IllegalArgumentException si no se encuentra una tarea con el ID proporcionado.
     */
    public TaskDTOResponse getTaskById(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Tarea no encontrada con ID: " + id));
        TaskDTOResponse response = new TaskDTOResponse();
        response.setId(task.getId());
        response.setTitle(task.getTitle());
        response.setDescription(task.getDescription());

        return response;
    }

    /**
     * Elimina una tarea por su ID.
     *
     * @param id ID de la tarea a eliminar.
     * @return TaskDTOResponse con los detalles de la tarea eliminada.
     * @throws IllegalArgumentException si no se encuentra la tarea con el ID proporcionado.
     */
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

    /**
     * Actualiza una tarea existente por su ID.
     *
     * @param id      ID de la tarea a actualizar.
     * @param request Objeto TaskDTORequest con los nuevos datos para la tarea.
     * @return TaskDTOResponse con los detalles de la tarea actualizada.
     * @throws IllegalArgumentException si no se encuentra la tarea con el ID proporcionado.
     */
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


    /**
     * Asigna una tarea a un usuario existente por sus IDs.
     *
     * @param taskId ID de la tarea a asignar.
     * @param userId ID del usuario al que se asignará la tarea.
     * @return TaskDTOResponse con los detalles de la tarea asignada.
     * @throws IllegalArgumentException si no se encuentra la tarea o el usuario con los IDs proporcionados.
     */
    public TaskDTOResponse assignTaskToUser(Long taskId, Long userId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new IllegalArgumentException("Tarea no encontrada con ID: " + taskId));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado con ID: " + userId));
        task.setUser(user);
        Task savedTask = taskRepository.save(task);
        TaskDTOResponse response = new TaskDTOResponse();
        response.setId(savedTask.getId());
        response.setTitle(savedTask.getTitle());
        response.setDescription(savedTask.getDescription());
        response.setUserId(user.getId());
        return response;
    }

}
