package com.brasilia.prueba.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * The type Task dto.
 */
@Data
public class TaskDto {
    private Long id;
    private String title;
    private String description;
    private String status;
    private Long userId; // Para representar el ID del usuario asociado a esta tarea

    /**
     * Instantiates a new Task dto.
     */
    public TaskDto() {
    }

    /**
     * Instantiates a new Task dto.
     *
     * @param id          the id
     * @param title       the title
     * @param description the description
     * @param status      the status
     * @param userId      the user id
     */
    public TaskDto(Long id, String title, String description, String status, Long userId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.userId = userId;
    }
}