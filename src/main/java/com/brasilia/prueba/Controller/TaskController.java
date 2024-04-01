package com.brasilia.prueba.Controller;

import com.brasilia.prueba.Dto.TaskDto;
import com.brasilia.prueba.Entity.TasksEntity;
import com.brasilia.prueba.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;

/**
 * The type Task controller.
 */
@RestController
@RequestMapping("/api/task")
public class TaskController {

    /**
     * The Task service.
     */
    @Autowired
    TaskService taskService;

    /**
     * Get tasks array list.
     *
     * @return the array list
     */
    @GetMapping
    public ArrayList<TaskDto> getTasks(){
        return (ArrayList<TaskDto>) this.taskService.getAllTasks();
    }

    /**
     * Gets task by id.
     *
     * @param id the id
     * @return the task by id
     */
    @GetMapping(path = "/{id}")
    public TaskDto getTaskById(@PathVariable("id") Long id){
        return this.taskService.getTaskById(id);
    }

    /**
     * Save task tasks entity.
     *
     * @param task the task
     * @return the tasks entity
     */
    @PostMapping
    public TasksEntity saveTask(@RequestBody TasksEntity task){
        return this.taskService.saveTask(task);
    }

    /**
     * Update task task dto.
     *
     * @param task the task
     * @param id   the id
     * @return the task dto
     */
    @PutMapping(path = "/{id}")
    public TaskDto updateTask(@RequestBody TaskDto task, @PathVariable("id") Long id){
        return this.taskService.updateTask(task, id);
    }

    /**
     * Delete task string.
     *
     * @param id the id
     * @return the string
     */
    @DeleteMapping(path = "/{id}")
    public String deleteTask(@PathVariable("id") Long id){
        boolean ok = this.taskService.deleteTask(id);
        if (ok){
            return "Task deleted";
        } else {
            return "Task not deleted";
        }
    }

    /**
     * Delete task response entity.
     *
     * @param userId the user id
     * @param taskId the task id
     * @return the response entity
     */
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

    /**
     * Update task status response entity.
     *
     * @param taskId      the task id
     * @param requestBody the request body
     * @return the response entity
     */
    @PutMapping("/{taskId}/status")
    public ResponseEntity<?> updateTaskStatus(@PathVariable Long taskId, @RequestBody Map<String, String> requestBody) {
        String newStatus = requestBody.get("newStatus");
        try {
            taskService.changeTaskStatus(taskId, newStatus);
            return ResponseEntity.ok("Estado de la tarea actualizado correctamente");
        } catch (IllegalAccessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
