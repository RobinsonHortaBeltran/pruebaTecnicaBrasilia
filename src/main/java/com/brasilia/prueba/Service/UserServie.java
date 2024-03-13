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

@Service
public class UserServie {

    @Autowired
    IUserRepository userRepository;

    @Autowired
    TaskService taskService;

    public List<UserDto> getAllUsers() {
        return this.userRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public Optional<UserDto> getUserById(Long id) {
        Optional<UserEntity> user = this.userRepository.findById(id);
        return user.map(this::convertToDto);
    }

    public UserEntity saveUser(UserDto userDto) {
        UserEntity user = this.convertToEntity(userDto);
        return this.userRepository.save(user);
    }

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

    public boolean deleteUser(Long id) {
        try {
            this.userRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public UserDto convertToDto(UserEntity user){
        List<TaskDto> taskDtos = user.getTasks().stream()
                .map(taskService::convertToDto)
                .collect(Collectors.toList());

        return new UserDto(user.getId(), user.getName(), taskDtos);
    }

    public UserEntity convertToEntity(UserDto userDto){
        UserEntity user = new UserEntity();
        user.setName(userDto.getName());
        return user;
    }
}
