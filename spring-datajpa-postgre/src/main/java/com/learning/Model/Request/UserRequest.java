package com.learning.Model.Request;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserRequest {

    private Long id;

    private String username;

    private boolean status;

    private List<AddressRequest> address;
}
