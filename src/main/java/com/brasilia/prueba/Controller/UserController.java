package com.brasilia.prueba.Controller;

import com.brasilia.prueba.Dto.UserDto;
import com.brasilia.prueba.Entity.UserEntity;
import com.brasilia.prueba.Service.UserServie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserServie userServie;

    @GetMapping
    public ArrayList<UserDto> getUsers(){
        return (ArrayList<UserDto>) this.userServie.getAllUsers();
    }

    @GetMapping(path = "/{id}")
    public Optional<UserDto> getUserById(@PathVariable("id") Long id){
       return this.userServie.getUserById(id);
    }

    @PostMapping("/")
    public UserEntity saveUser(@RequestBody UserDto user){
        return this.userServie.saveUser(user);
    }

    @PutMapping(path = "/{id}")
    public Optional<UserDto> updateUser(@RequestBody UserDto user, @PathVariable("id") Long id){
        return this.userServie.updateUser(user, id);
    }

    @DeleteMapping(path = "/{id}")
    public String deleteUser(@PathVariable("id") Long id){
        boolean ok = this.userServie.deleteUser(id);
        if (ok){
            return "User deleted";
        } else {
            return "User not deleted";
        }
    }

}
