package com.learning.Manager.Response;

import com.learning.DTO.AddressDTO;
import com.learning.Model.Response.AddressResponse;
import org.springframework.stereotype.Component;

@Component
public class AddressResponseManager implements ResponseManager {

    public AddressResponse buildAddressResponse(AddressDTO addressDTO) {
        AddressResponse addressResponse = new AddressResponse();
        addressResponse.setId(addressDTO.getId());
        addressResponse.setAddress(addressDTO.getAddress());
        addressResponse.setType(addressDTO.getType());
        addressResponse.setStatus(addressDTO.isStatus());
        addressResponse.setUserId(addressDTO.getUserId());

        return addressResponse;
    }
}
