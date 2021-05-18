package com.learning.Model.Response;

import com.learning.DTO.AddressDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserResponse {
    private Long id;
    private String username;
    private List<AddressDTO> address;
    private boolean status;
}
