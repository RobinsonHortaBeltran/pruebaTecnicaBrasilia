package com.brasilia.prueba.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "tasks")
@Data
public class TasksEntity {
    //Id is the primary key of the table
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Column title is the title of the column in the table
    @Column(name = "title", nullable = false)
    private String title;

    //Column description is the description of the column in the table
    @Column(name = "description", nullable = false)
    private String description;

    //Column status is the status of the column in the table
    @Column(name = "status", nullable = false)
    private String status;

    //Relation one to many with the tasks table
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private UserEntity user;

}
