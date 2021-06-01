package com.learning.DTO;

import com.learning.Type.AddressType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDTO implements DTOInterface {

    private Long id;
    private String address;
    private boolean status;
    private AddressType type;
    private Long userId;
    private Long cityId;
    private Long countryId;
}
