package com.mycompany.CrudSpringBoot.controller;

import com.mycompany.CrudSpringBoot.dto.UserDTO;
import com.mycompany.CrudSpringBoot.entity.User;
import com.mycompany.CrudSpringBoot.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserDTO userDTO) {
        UUID userId = userService.createUser(userDTO);
        return ResponseEntity.created(URI.create("/users/" + userId.toString())).build();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable("userId") String userId) {
        Optional<User> user = userService.findUserById(userId);

        if (user.isPresent())
            return ResponseEntity.ok(user.get());
        else
            return ResponseEntity.notFound().build();
    }

    @GetMapping()
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = userService.findAll();

        return ResponseEntity.ok(users);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUserById(@PathVariable("userId") String userId) {
        userService.deleteByID(userId);
        return ResponseEntity.noContent().build();
    }

}
