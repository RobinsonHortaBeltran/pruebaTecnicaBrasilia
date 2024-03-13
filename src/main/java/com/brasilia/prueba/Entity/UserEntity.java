package com.brasilia.prueba.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "users")
@Data
public class UserEntity {

    //Id is the primary key of the table
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Column name is the name of the column in the table
    @Column(name = "name", nullable = false, length = 50,unique = true)
    private String name;

    //Relation one to many with the tasks table
    @OneToMany(mappedBy = "user")
    private List<TasksEntity> tasks;


}
