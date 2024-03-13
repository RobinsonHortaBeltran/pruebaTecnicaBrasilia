package com.brasilia.prueba.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TaskDto {
    private Long id;
    private String title;
    private String description;
    private String status;
    private Long userId; // Para representar el ID del usuario asociado a esta tarea

    public TaskDto() {
    }

    public TaskDto(Long id, String title, String description, String status, Long userId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.userId = userId;
    }
}