package com.learning.Service;

import com.learning.Aggregator.AddressAggregator;
import com.learning.Aggregator.UserAggregator;
import com.learning.DTO.AddressDTO;
import com.learning.DTO.DTOInterface;
import com.learning.DTO.UserDTO;
import com.learning.Entity.Address;
import com.learning.Entity.User;
import com.learning.Repository.AddressRepository;
import com.learning.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements CrudService {

    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final AddressAggregator addressAggregator;
    private final UserAggregator userAggregator;
    private final AddressService addressService;

    @Override
    public DTOInterface update(DTOInterface dtoInterface) {
        try {
            UserDTO userDTO = (UserDTO) dtoInterface;
            Optional<User> user = userRepository.findById(userDTO.getId());
            if (user.isPresent()) {
                user.get().setUsername(userDTO.getUsername());
                user.get().setStatus(userDTO.isStatus());
                userDTO.getAddress().forEach(addressService::update);
                userRepository.save(user.get());
                return userAggregator.prepareDTOByEntity(user.get());
            }
            return null;
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return null;
        }
    }

    @Override
    @Transactional
    public DTOInterface create(DTOInterface dtoInterface) {
        try {
            UserDTO userDTO = (UserDTO) dtoInterface;
            User user = new User();
            user.setUsername(userDTO.getUsername());
            user.setStatus(userDTO.isStatus());
            userRepository.save(user);

            List<AddressDTO> addressDTOList = new ArrayList<>();
            userDTO.getAddress().forEach(item -> {
                Address address = new Address();
                address.setAddress(item.getAddress());
                address.setStatus(item.isStatus());
                address.setType(item.getType());
                address.setUser(user);
                addressRepository.save(address);
                addressDTOList.add(addressAggregator.prepareDTOByEntity(address));
            });

            userDTO.setId(user.getId());
            userDTO.setAddress(addressDTOList);

            return userDTO;
        } catch (Exception exception) {
            return null;
        }
    }

    @Override
    public List<DTOInterface> getAll() {
        List<DTOInterface> userDTOList = new ArrayList<>();
        List<User> userList = userRepository.findAll();
        userList.forEach(item -> userDTOList.add(userAggregator.prepareDTOByEntity(item)));

        return userDTOList;
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public DTOInterface getById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            return null;
        }

        return userAggregator.prepareDTOByEntity(user.get());
    }

    @Override
    public Page<DTOInterface> getAllWithPagination(Pageable pageable) {
        return null;
    }
}
