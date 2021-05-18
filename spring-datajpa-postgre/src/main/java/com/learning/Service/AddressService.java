package com.learning.Service;

import com.learning.Aggregator.AddressAggregator;
import com.learning.DTO.AddressDTO;
import com.learning.DTO.DTOInterface;
import com.learning.Entity.Address;
import com.learning.Repository.AddressRepository;
import com.learning.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AddressService implements CrudService{

    private final AddressRepository addressRepository;
    private final UserRepository userRepository;
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
        AddressDTO addressDTO = (AddressDTO) dtoInterface;

        Address addressEntity = new Address();
        addressEntity.setAddress(addressDTO.getAddress());
        addressEntity.setStatus(addressDTO.isStatus());
        addressEntity.setType(addressDTO.getType());
        addressEntity.setUser(userRepository.findById(addressDTO.getUserId()).get());
        addressRepository.save(addressEntity);

        return addressAggregator.prepareDTOByEntity(addressEntity);
    }

    @Override
    public List<DTOInterface> getAll() {
        List<DTOInterface> addressDTOList = new ArrayList<>();
        addressRepository.findAll().forEach(value -> addressDTOList.add(addressAggregator.prepareDTOByEntity(value)));
        return addressDTOList;
    }

    @Override
    public void deleteById(Long id) {
        addressRepository.deleteById(id);
    }

    @Override
    public DTOInterface getById(Long id) {
        Optional<Address> addressEntity = addressRepository.findById(id);
        if (addressEntity.isEmpty()) {
            return null;
        }

        return addressAggregator.prepareDTOByEntity(addressEntity.get());
    }

    @Override
    public Page<DTOInterface> getAllWithPagination(Pageable pageable) {
        return null;
    }
}