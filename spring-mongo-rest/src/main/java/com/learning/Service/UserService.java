package com.learning.Service;

import com.learning.Entity.User;
import com.learning.Exception.UserNotFoundException;
import com.learning.Repository.UserRepository;
import com.learning.Request.PostUserRequest;
import org.springframework.stereotype.Service;

import java.util.List;

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
        User user = userRepository.findById(userRequest.getId()).stream().
                findFirst().orElseThrow(UserNotFoundException::new);
        user.setUsername(userRequest.getUsername());
        userRepository.save(user);

        return user;
    }

    public boolean deleteUser(String id) {
        User user = userRepository.findById(id).stream().
                findFirst().orElseThrow(UserNotFoundException::new);

        try {
            userRepository.deleteById(user.getId());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    public User getUserById(String id) {
        return userRepository.findById(id).stream().findFirst().orElseThrow(UserNotFoundException::new);
    }
}
