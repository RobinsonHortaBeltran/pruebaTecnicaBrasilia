package com.brasilia.prueba.Repository;

import com.brasilia.prueba.Entity.TasksEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITaskRepository extends JpaRepository<TasksEntity, Long>{

}
