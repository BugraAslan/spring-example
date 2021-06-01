package com.learning.Model.Request;

import com.learning.Type.AddressType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressRequest {

    private Long id;
    private String address;
    private boolean status;
    private AddressType type;
    private Long cityId;
    private Long countryId;

}
