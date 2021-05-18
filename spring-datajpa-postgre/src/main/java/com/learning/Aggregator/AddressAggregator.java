package com.learning.Aggregator;

import com.learning.DTO.AddressDTO;
import com.learning.Entity.Address;
import org.springframework.stereotype.Component;

@Component
public class AddressAggregator {

    public AddressDTO prepareDTOByEntity(Address address) {
        AddressDTO AddressDTO = new AddressDTO();
        AddressDTO.setId(address.getId());
        AddressDTO.setAddress(address.getAddress());
        AddressDTO.setType(address.getType());
        AddressDTO.setStatus(address.isStatus());

        return AddressDTO;
    }
}
