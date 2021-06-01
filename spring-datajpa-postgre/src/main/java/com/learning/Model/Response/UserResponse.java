package com.learning.Model.Response;

import com.learning.DTO.AddressDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private boolean status;
    private List<AddressDTO> address;
}
