package com.learning.Aggregator;

import com.learning.DTO.AddressDTO;
import com.learning.DTO.PaginationDTO;
import com.learning.DTO.UserDTO;
import com.learning.Entity.Address;
import com.learning.Entity.User;
import com.learning.Model.Request.AddressRequest;
import com.learning.Model.Request.UserRequest;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserAggregator extends PaginationAggregator {

    public UserDTO prepareDTOByRequest(UserRequest userRequest) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(userRequest.getId());
        userDTO.setUsername(userRequest.getUsername());
        userDTO.setStatus(userRequest.isStatus());

        List<AddressDTO> addressDTOList = new ArrayList<>();
        if (userRequest.getAddress() != null) {
            for (AddressRequest addressRequest: userRequest.getAddress()) {
                AddressDTO addressDTO = new AddressDTO();
                addressDTO.setId(addressRequest.getId());
                addressDTO.setAddress(addressRequest.getAddress());
                addressDTO.setType(addressRequest.getType());
                addressDTO.setStatus(addressRequest.isStatus());
                addressDTOList.add(addressDTO);
            }

            userDTO.setAddress(addressDTOList);
        }

        return userDTO;
    }

    public UserDTO prepareDTOByEntity(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setStatus(user.isStatus());

        List<AddressDTO> addressDTOList = new ArrayList<>();
        if (user.getAddresses() != null) {
            for (Address address: user.getAddresses()) {
                AddressDTO addressDTO = new AddressDTO();
                addressDTO.setId(address.getId());
                addressDTO.setAddress(address.getAddress());
                addressDTO.setType(address.getType());
                addressDTO.setStatus(address.isStatus());
                addressDTOList.add(addressDTO);
            }

            userDTO.setAddress(addressDTOList);
        }

        return userDTO;
    }
}
