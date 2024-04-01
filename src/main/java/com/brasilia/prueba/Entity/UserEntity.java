package com.brasilia.prueba.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * The type User entity.
 */
@Entity
@Table(name = "users")
@Data
public class UserEntity {

    //Id is the primary key of the table
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    //Column name is the name of the column in the table
    @Column(name = "name", nullable = false, length = 50,unique = true)
    private String name;

    //Relation one to many with the tasks table
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore // Para evitar la recursión infinita en la serialización JSON
    private List<TasksEntity> tasks = new ArrayList<>();

}
