package com.brasilia.prueba.Repository;

import com.brasilia.prueba.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface User repository.
 */
@Repository
public interface IUserRepository extends JpaRepository<UserEntity, Long> {
}
