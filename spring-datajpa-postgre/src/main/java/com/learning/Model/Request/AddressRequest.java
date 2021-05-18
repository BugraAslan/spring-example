package com.learning.Model.Request;

import com.learning.Type.AddressType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressRequest {

    public Long id;
    public String address;
    public boolean status;
    public AddressType type;
}
