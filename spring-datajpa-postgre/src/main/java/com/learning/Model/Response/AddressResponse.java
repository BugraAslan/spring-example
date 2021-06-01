package com.learning.Model.Response;

import com.learning.Type.AddressType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressResponse {

    private Long id;
    private String address;
    private AddressType type;
    private boolean status;
    private Long userId;
    private Long cityId;
    private Long countryId;
}
