package com.brasilia.prueba.Controller;

import com.brasilia.prueba.Dto.UserDto;
import com.brasilia.prueba.Entity.UserEntity;
import com.brasilia.prueba.Service.UserServie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

/**
 * The type User controller.
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    /**
     * The User servie.
     */
    @Autowired
    UserServie userServie;

    /**
     * Get users array list.
     *
     * @return the array list
     */
    @GetMapping
    public ArrayList<UserDto> getUsers(){
        return (ArrayList<UserDto>) this.userServie.getAllUsers();
    }

    /**
     * Gets user by id.
     *
     * @param id the id
     * @return the user by id
     */
    @GetMapping(path = "/{id}")
    public Optional<UserDto> getUserById(@PathVariable("id") Long id){
       return this.userServie.getUserById(id);
    }

    /**
     * Save user user entity.
     *
     * @param user the user
     * @return the user entity
     */
    @PostMapping("/")
    public UserEntity saveUser(@RequestBody UserDto user){
        return this.userServie.saveUser(user);
    }

    /**
     * Update user optional.
     *
     * @param user the user
     * @param id   the id
     * @return the optional
     */
    @PutMapping(path = "/{id}")
    public Optional<UserDto> updateUser(@RequestBody UserDto user, @PathVariable("id") Long id){
        return this.userServie.updateUser(user, id);
    }

    /**
     * Delete user string.
     *
     * @param id the id
     * @return the string
     */
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
