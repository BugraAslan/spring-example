package com.learning.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserDTO implements DTOInterface {

    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private boolean status;
    private List<AddressDTO> address;
}
