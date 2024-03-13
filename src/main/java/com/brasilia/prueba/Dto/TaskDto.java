package com.brasilia.prueba.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TaskDto {

    //Id is the primary key of the table
        private Long id;
        //Column title is the title of the column in the table
        @JsonProperty("title")
        private String title;

        //Column description is the description of the column in the table
        @JsonProperty("description")
        private String description;

        //Column status is the status of the column in the table
        @JsonProperty("status")
        private String status;

        public TaskDto() {
        }

        public TaskDto(Long id, String title, String description, String status) {
            this.id = id;
            this.title = title;
            this.description = description;
            this.status = status;
        }
}
