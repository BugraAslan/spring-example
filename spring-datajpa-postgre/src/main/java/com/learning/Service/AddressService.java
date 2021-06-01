package com.learning.Service;

import com.learning.Aggregator.AddressAggregator;
import com.learning.DTO.AddressDTO;
import com.learning.DTO.DTOInterface;
import com.learning.Entity.Address;
import com.learning.Exception.RecordNotFoundException;
import com.learning.Repository.AddressRepository;
import com.learning.Repository.CityRepository;
import com.learning.Repository.CountryRepository;
import com.learning.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressService implements CrudService {

    private final AddressAggregator addressAggregator;
    private final AddressRepository addressRepository;
    private final UserRepository userRepository;
    private final CountryRepository countryRepository;
    private final CityRepository cityRepository;

    @Override
    @Transactional
    public DTOInterface update(DTOInterface dtoInterface) {
        AddressDTO addressDTO = (AddressDTO) dtoInterface;
        Address address = addressRepository.findById(addressDTO.getId()).stream().findFirst()
                .orElseThrow(() -> new RecordNotFoundException("Address not found"));

        address.setAddress(addressDTO.getAddress());
        address.setStatus(addressDTO.isStatus());
        address.setType(addressDTO.getType());
        cityRepository.findById(addressDTO.getCityId()).ifPresent(address::setCity);
        countryRepository.findById(addressDTO.getCountryId()).ifPresent(address::setCountry);
        addressRepository.save(address);

        return addressAggregator.prepareDTOByEntity(address);
    }

    @Override
    @Transactional
    public DTOInterface create(DTOInterface dtoInterface) {
        AddressDTO addressDTO = (AddressDTO) dtoInterface;
        Address addressEntity = new Address();
        addressEntity.setAddress(addressDTO.getAddress());
        addressEntity.setStatus(addressDTO.isStatus());
        addressEntity.setType(addressDTO.getType());
        userRepository.findById(addressDTO.getUserId()).ifPresent(addressEntity::setUser);
        cityRepository.findById(addressDTO.getCityId()).ifPresent(addressEntity::setCity);
        countryRepository.findById(addressDTO.getCountryId()).ifPresent(addressEntity::setCountry);
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
        return addressAggregator.prepareDTOByEntity(
                addressRepository.findById(id).stream().findFirst()
                        .orElseThrow(() -> new RecordNotFoundException("Address not found"))
        );
    }
}