package com.learning.Controller;

import com.learning.Entity.User;
import com.learning.Request.PostUserRequest;
import com.learning.Service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody PostUserRequest userRequest) {
        if (userRequest.getUsername().isEmpty()) {
            return null;
        }

        User user = userService.createUser(userRequest);
        if (user == null) {
            return new ResponseEntity<>("User not created", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PatchMapping()
    public ResponseEntity<Object> update(@RequestBody PostUserRequest userRequest) {
        User user = userService.updateUser(userRequest);
        if (user == null) {
            return new ResponseEntity<>("Kullanıcı Bulunamadı", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable String id) {
        boolean isDeleted = userService.deleteUser(id);
        if (!isDeleted) {
            return new ResponseEntity<>("User not deleted", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("User deleted", HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getUserById(@PathVariable String id) {
        User user = userService.getUserById(id);
        if (user == null) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/list")
    public List<User> getUsers() {
        return userService.getAllUser();
    }
}
