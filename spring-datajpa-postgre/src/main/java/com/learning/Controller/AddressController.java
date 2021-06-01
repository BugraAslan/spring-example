package com.learning.Controller;

import com.learning.DTO.AddressDTO;
import com.learning.Exception.RecordNotFoundException;
import com.learning.Manager.Response.AddressResponseManager;
import com.learning.Model.Response.AddressResponse;
import com.learning.Model.Response.BaseResponseModel;
import com.learning.Service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/address")
public class AddressController extends BaseController{

    private final AddressService addressService;
    private final AddressResponseManager addressResponseManager;

    @GetMapping("/{id}")
    public BaseResponseModel getById(@PathVariable Long id) {
        return response.successResponse(
                addressResponseManager.buildAddressResponse(
                        (AddressDTO) addressService.getById(id)
                )
        );
    }

    @GetMapping("/list")
    public BaseResponseModel getAllAddress() {
        List<AddressResponse> addressResponseList = new ArrayList<>();
        addressService.getAll().forEach(item ->
                addressResponseList.add(
                        addressResponseManager.buildAddressResponse((AddressDTO) item)
                )
        );

        if (addressResponseList.isEmpty()) {
            throw new RecordNotFoundException("Address not found");
        }

        return response.successResponse(addressResponseList);
    }
}
