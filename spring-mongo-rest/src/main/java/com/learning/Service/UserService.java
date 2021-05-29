package com.learning.Service;

import com.learning.Entity.User;
import com.learning.Repository.UserRepository;
import com.learning.Request.PostUserRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(PostUserRequest userRequest) {
        if (userRequest.getUsername().isEmpty()) {
            return null;
        }

        User user = new User();
        user.setUsername(userRequest.getUsername());
        userRepository.save(user);

        return user;
    }

    public User updateUser(PostUserRequest userRequest) {
        Optional<User> user = userRepository.findById(userRequest.getId());
        if (user.isEmpty()) {
            return null;
        }

        user.get().setUsername(userRequest.getUsername());
        userRepository.save(user.get());

        return user.get();
    }

    public boolean deleteUser(String id) {
        if (userRepository.findById(id).isEmpty()) {
            return false;
        }
        userRepository.deleteById(id);

        return true;
    }

    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    public User getUserById(String id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            return null;
        }

        return user.get();
    }
}
