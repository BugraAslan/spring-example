package com.learning.Manager.Response;

import com.learning.DTO.UserDTO;
import com.learning.Model.Response.UserResponse;
import org.springframework.stereotype.Component;

@Component
public class UserResponseManager implements ResponseManager{

    public UserResponse buildUserResponse(UserDTO userDTO) {
        UserResponse userResponse = new UserResponse();
        userResponse.setUsername(userDTO.getUsername());
        userResponse.setId(userDTO.getId());
        userResponse.setStatus(userDTO.isStatus());
        userResponse.setAddress(userDTO.getAddress());

        return userResponse;
    }
}
