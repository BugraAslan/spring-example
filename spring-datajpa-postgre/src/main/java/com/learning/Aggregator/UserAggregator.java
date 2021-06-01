package com.learning.Aggregator;

import com.learning.DTO.AddressDTO;
import com.learning.DTO.UserDTO;
import com.learning.Entity.User;
import com.learning.Model.Request.UserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UserAggregator extends PaginationAggregator {

    private final AddressAggregator addressAggregator;

    public UserDTO prepareDTOByRequest(UserRequest userRequest) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(userRequest.getId());
        userDTO.setStatus(userRequest.isStatus());
        userDTO.setFirstName(userRequest.getFirstName());
        userDTO.setLastName(userRequest.getLastName());
        userDTO.setPhoneNumber(userRequest.getPhoneNumber());
        userDTO.setEmail(userRequest.getEmail());

        List<AddressDTO> addressDTOList = new ArrayList<>();
        userRequest.getAddress().forEach(value -> {
                    AddressDTO addressDTO = new AddressDTO();
                    addressDTO.setId(value.getId());
                    addressDTO.setAddress(value.getAddress());
                    addressDTO.setStatus(value.isStatus());
                    addressDTO.setType(value.getType());
                    addressDTO.setCityId(value.getCityId());
                    addressDTO.setCountryId(value.getCountryId());
                    addressDTOList.add(addressDTO);
                }
        );
        userDTO.setAddress(addressDTOList);

        return userDTO;
    }

    public UserDTO prepareDTOByEntity(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setStatus(user.isStatus());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPhoneNumber(user.getPhoneNumber());

        List<AddressDTO> addressDTOList = new ArrayList<>();
        user.getAddress().forEach(value -> addressDTOList.add(this.addressAggregator.prepareDTOByEntity(value)));
        userDTO.setAddress(addressDTOList);

        return userDTO;
    }
}
