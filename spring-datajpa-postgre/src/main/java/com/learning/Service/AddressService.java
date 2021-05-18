package com.learning.Service;

import com.learning.Aggregator.AddressAggregator;
import com.learning.DTO.AddressDTO;
import com.learning.DTO.DTOInterface;
import com.learning.Entity.Address;
import com.learning.Repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AddressService implements CrudService{

    private final AddressRepository addressRepository;
    private final AddressAggregator addressAggregator;

    @Override
    public DTOInterface update(DTOInterface dtoInterface) {
        AddressDTO addressDTO = (AddressDTO) dtoInterface;
        Optional<Address> address = addressRepository.findById(addressDTO.getId());
        if (address.isEmpty()) {
            return null;
        }

        address.get().setAddress(addressDTO.getAddress());
        address.get().setStatus(addressDTO.isStatus());
        address.get().setType(addressDTO.getType());
        addressRepository.save(address.get());

        return addressAggregator.prepareDTOByEntity(address.get());
    }

    @Override
    public DTOInterface create(DTOInterface dtoInterface) {
        return null;
    }

    @Override
    public List<DTOInterface> getAll() {
        return null;
    }

    @Override
    public void deleteById(Long id) {
        addressRepository.deleteById(id);
    }

    @Override
    public DTOInterface getById(Long id) {
        return null;
    }

    @Override
    public Page<DTOInterface> getAllWithPagination(Pageable pageable) {
        return null;
    }
}