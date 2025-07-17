package com.mycompany.CrudSpringBoot.service;

import com.mycompany.CrudSpringBoot.dto.UserDTO;
import com.mycompany.CrudSpringBoot.entity.User;
import com.mycompany.CrudSpringBoot.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;

    public UUID createUser(UserDTO userDTO) {

        User user = new User(
                null,
                userDTO.username(),
                userDTO.email(),
                userDTO.password(),
                Instant.now(),
                null
        );

        User userSaved = userRepository.save(user);

        return userSaved.getUserId();
    }

    public Optional<User> findUserById(String userId) {

        return userRepository.findById(UUID.fromString(userId));
    }
    
    public List<User> findAll() {
        return userRepository.findAll();
    }

    public void deleteByID(String userId) {

        UUID id = UUID.fromString(userId);

        boolean userExists = userRepository.existsById(id);

        if (userExists) {
            userRepository.deleteById(id);
        }
    }

}
