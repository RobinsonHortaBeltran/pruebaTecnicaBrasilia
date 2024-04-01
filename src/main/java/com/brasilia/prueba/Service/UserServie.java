package com.brasilia.prueba.Service;

import com.brasilia.prueba.Dto.TaskDto;
import com.brasilia.prueba.Dto.UserDto;
import com.brasilia.prueba.Entity.UserEntity;
import com.brasilia.prueba.Repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * The type User servie.
 */
@Service
public class UserServie {

    @Autowired
    private final IUserRepository userRepository;

    /**
     * The Task service.
     */
    @Autowired
    TaskService taskService;

    /**
     * Instantiates a new User servie.
     *
     * @param userRepository the user repository
     */
    public UserServie(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Gets all users.
     *
     * @return the all users
     */
    public List<UserDto> getAllUsers() {
        return this.userRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    /**
     * Gets user by id.
     *
     * @param id the id
     * @return the user by id
     */
    public Optional<UserDto> getUserById(Long id) {
        Optional<UserEntity> user = this.userRepository.findById(id);
        return user.map(this::convertToDto);
    }

    /**
     * Save user user entity.
     *
     * @param userDto the user dto
     * @return the user entity
     */
    public UserEntity saveUser(UserDto userDto) {
        UserEntity user = this.convertToEntity(userDto);
        return this.userRepository.save(user);
    }

    /**
     * Update user optional.
     *
     * @param request the request
     * @param id      the id
     * @return the optional
     */
    public Optional<UserDto> updateUser(UserDto request, Long id) {
        Optional<UserEntity> user = this.userRepository.findById(id);
        if (user.isPresent()) {
            UserEntity existingUser = user.get();
            existingUser.setName(request.getName());
            UserEntity updatedUser = this.userRepository.save(existingUser);
            return Optional.of(this.convertToDto(updatedUser));
        } else {
            return Optional.empty();
        }
    }

    /**
     * Delete user boolean.
     *
     * @param id the id
     * @return the boolean
     */
    public boolean deleteUser(Long id) {
        try {
            this.userRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Convert to dto user dto.
     *
     * @param user the user
     * @return the user dto
     */
    public UserDto convertToDto(UserEntity user){
        List<TaskDto> taskDtos = user.getTasks().stream()
                .map(taskService::convertToDto)
                .collect(Collectors.toList());

        return new UserDto(user.getId(), user.getName(), taskDtos);
    }

    /**
     * Convert to entity user entity.
     *
     * @param userDto the user dto
     * @return the user entity
     */
    public UserEntity convertToEntity(UserDto userDto){
        UserEntity user = new UserEntity();
        user.setName(userDto.getName());
        return user;
    }
}
