package com.brasilia.prueba.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * The type User dto.
 */
@Data
public class UserDto {
        //Id is the primary key of the table
        private Long id;

        //Column name is the name of the column in the table
        @JsonProperty("name")
        private String name;

        //Relation one to many with the tasks table
        private List<TaskDto> tasks;

    /**
     * Instantiates a new User dto.
     */
    public UserDto() {
        }

    /**
     * Instantiates a new User dto.
     *
     * @param id    the id
     * @param name  the name
     * @param tasks the tasks
     */
    public UserDto(Long id, String name,List<TaskDto> tasks) {
            this.id = id;
            this.name = name;
            this.tasks = tasks;
        }

}
