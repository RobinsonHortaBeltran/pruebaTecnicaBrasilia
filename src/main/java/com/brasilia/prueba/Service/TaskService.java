package com.brasilia.prueba.Service;

import com.brasilia.prueba.Dto.TaskDto;
import com.brasilia.prueba.Entity.TasksEntity;
import com.brasilia.prueba.Entity.UserEntity;
import com.brasilia.prueba.Repository.ITaskRepository;
import com.brasilia.prueba.Repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskService {

    @Autowired
    ITaskRepository taskRepository;
    IUserRepository userRepository;
    @Autowired
    UserServie userServie;


    public List<TaskDto> getAllTasks() {
        return this.taskRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public TaskDto getTaskById(Long id) {
        TasksEntity task = this.taskRepository.findById(id).orElse(null);
        assert task != null;
        return this.convertToDto(task);
    }

    public TasksEntity saveTask(TasksEntity task) {
        UserServie userService = new UserServie(userRepository);
        task.setTitle(task.getTitle());
        task.setDescription(task.getDescription());
        task.setStatus(task.getStatus());
        task.setUser(task.getUser());

        return this.taskRepository.save(task);
    }

    public TaskDto updateTask(TaskDto request, Long id) {
        TasksEntity task = this.taskRepository.findById(id).orElse(null);
        assert task != null;
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setStatus(request.getStatus());
        return this.convertToDto(this.taskRepository.save(task));
    }

    public boolean deleteTask(Long id) {
        try {
            this.taskRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void deleteTaskUserIdAndTaskId(Long userId, Long taskId) throws IllegalAccessException {
        try {
            TasksEntity task = this.taskRepository.findById(taskId).orElse(null);
            if (task != null && task.getUser().getId().equals(userId)) {
                taskRepository.delete(task);
            } else {
                throw new IllegalAccessException("No se encontró la tarea para el usuario especificado");
            }
        } catch (Exception e) {
            throw new IllegalAccessException("Error al eliminar la tarea: " + e.getMessage());
        }
    }

    public void changeTaskStatus(Long taskId, String newStatus) throws IllegalAccessException {
        try {
        TasksEntity task = taskRepository.findById(taskId).orElse(null);
            if (task != null) {
                task.setStatus(newStatus);
                taskRepository.save(task);
            } else {
                throw new IllegalAccessException("La tarea no existe");
            }
        } catch (Exception e) {
            throw new IllegalAccessException("Error al cambiar el estado de la tarea");
        }
    }
    public TaskDto convertToDto(TasksEntity task) {
        TaskDto taskDto = new TaskDto();
        taskDto.setId(task.getId());
        taskDto.setTitle(task.getTitle());
        taskDto.setDescription(task.getDescription());
        taskDto.setStatus(task.getStatus());
        taskDto.setUserId(task.getUser().getId());
        return taskDto;
    }

    public TasksEntity convertToEntity(TaskDto taskDto) {
        TasksEntity task = new TasksEntity();
        //task.setId(taskDto.getId());
        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        task.setStatus(taskDto.getStatus());
        Optional<UserEntity> userOptional = userRepository.findById(taskDto.getUserId());

        // Verificar si el usuario existe en el repositorio
        if (userOptional.isPresent()) {
            // Asignar el usuario a la tarea
            task.setUser(userOptional.get());
        } else {
            // Manejar el caso en el que el usuario no existe
            // Puedes lanzar una excepción, asignar un usuario por defecto, o manejarlo de alguna otra manera
            // Por ejemplo, lanzar una excepción
            throw new IllegalArgumentException("El usuario con el ID " + taskDto.getUserId() + " no fue encontrado.");
        }

        return task;
    }
}
