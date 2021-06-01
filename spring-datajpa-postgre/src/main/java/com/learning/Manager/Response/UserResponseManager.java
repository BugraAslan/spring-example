package com.learning.Manager.Response;

import com.learning.DTO.UserDTO;
import com.learning.Model.Response.UserResponse;
import org.springframework.stereotype.Component;

@Component
public class UserResponseManager extends PaginationResponseManager implements ResponseManager {

    public UserResponse buildUserResponse(UserDTO userDTO) {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(userDTO.getId());
        userResponse.setFirstName(userDTO.getFirstName());
        userResponse.setLastName(userDTO.getLastName());
        userResponse.setEmail(userDTO.getEmail());
        userResponse.setStatus(userDTO.isStatus());
        userResponse.setPhoneNumber(userDTO.getPhoneNumber());

        if (userDTO.getAddress() != null) {
            userResponse.setAddress(userDTO.getAddress());
        }

        return userResponse;
    }
}
