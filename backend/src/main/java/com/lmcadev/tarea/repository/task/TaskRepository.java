package com.lmcadev.tarea.repository.task;

import com.lmcadev.tarea.model.task.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    Optional<Task> findById(Long id);
    Optional<Task> findByTitle(String title);
    void deleteById(Long id);
    void findByUserId(Long userId);

}
