package com.brasilia.prueba.Controller;

import com.brasilia.prueba.Dto.TaskDto;
import com.brasilia.prueba.Entity.TasksEntity;
import com.brasilia.prueba.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/task")
public class TaskController {

    @Autowired
    TaskService taskService;

    @GetMapping
    public ArrayList<TaskDto> getTasks(){
        return (ArrayList<TaskDto>) this.taskService.getAllTasks();
    }

    @GetMapping(path = "/{id}")
    public TaskDto getTaskById(@PathVariable("id") Long id){
        return this.taskService.getTaskById(id);
    }

    @PostMapping
    public TasksEntity saveTask(@RequestBody TaskDto task){
        return this.taskService.saveTask(task);
    }

    @PutMapping(path = "/{id}")
    public TaskDto updateTask(@RequestBody TaskDto task, @PathVariable("id") Long id){
        return this.taskService.updateTask(task, id);
    }

    @DeleteMapping(path = "/{id}")
    public String deleteTask(@PathVariable("id") Long id){
        boolean ok = this.taskService.deleteTask(id);
        if (ok){
            return "Task deleted";
        } else {
            return "Task not deleted";
        }
    }

    @DeleteMapping("/users/{userId}/tasks/{taskId}")
    public ResponseEntity<String> deleteTask(@PathVariable Long userId, @PathVariable Long taskId) {
        try {
            taskService.deleteTaskUserIdAndTaskId(userId, taskId);
            return ResponseEntity.ok("Tarea eliminada correctamente");
        } catch (IllegalAccessException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar la tarea");
        }
    }

    @PutMapping("/{taskId}/status")
    public ResponseEntity<String> updateTaskStatus(@PathVariable Long taskId, @RequestParam String newStatus) {
        try {
            taskService.changeTaskStatus(taskId, newStatus);
            return ResponseEntity.ok("Estado de la tarea actualizado correctamente");
        } catch (IllegalAccessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
