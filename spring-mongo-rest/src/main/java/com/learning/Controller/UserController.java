package com.learning.Controller;

import com.learning.Entity.User;
import com.learning.Repository.UserRepository;
import com.learning.Request.PostUserRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping
    public User create(@RequestBody PostUserRequest userRequest) {
        try {
            if (userRequest.getUsername().isEmpty()) {
                return null;
            } else {
                User user = new User();
                user.setUsername(userRequest.getUsername());
                userRepository.save(user);
                return user;
            }
        } catch (Exception exception) {
            return null;
        }
    }

    @PatchMapping()
    public Optional<User> update(@RequestBody PostUserRequest userRequest) {
        try {
            Optional<User> user = userRepository.findById(userRequest.getId());
            if (user.isPresent()) {
                user.get().setUsername(userRequest.getUsername());
                userRepository.save(user.get());
            }
            return user;
        } catch (Exception exception) {
            return Optional.empty();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable String id) {
        userRepository.deleteById(id);
        return ResponseEntity.ok("User deleted");
    }

    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable String id) {
        return userRepository.findById(id);
    }

    @GetMapping("/list")
    public List<User> getUsers() {
        return userRepository.findAll();
    }
}
