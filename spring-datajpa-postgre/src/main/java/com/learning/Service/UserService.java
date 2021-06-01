package com.learning.Service;

import com.learning.Aggregator.UserAggregator;
import com.learning.DTO.AddressDTO;
import com.learning.DTO.DTOInterface;
import com.learning.DTO.UserDTO;
import com.learning.Entity.User;
import com.learning.Exception.RecordNotFoundException;
import com.learning.Model.Request.PaginationRequest;
import com.learning.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService implements CrudService {

    private final UserAggregator userAggregator;
    private final AddressService addressService;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public DTOInterface update(DTOInterface dtoInterface) {
            UserDTO userDTO = (UserDTO) dtoInterface;
            User user = userRepository.findById(userDTO.getId()).stream().findFirst()
                    .orElseThrow(() -> new RecordNotFoundException("User not found"));

            user
                    .setFirstName(userDTO.getFirstName())
                    .setLastName(userDTO.getLastName())
                    .setEmail(userDTO.getEmail())
                    .setPhoneNumber(userDTO.getPhoneNumber())
                    .setStatus(userDTO.isStatus());
            userDTO.getAddress().forEach(addressService::update);
            userRepository.save(user);

            return userAggregator.prepareDTOByEntity(user);
    }

    @Override
    @Transactional
    public DTOInterface create(DTOInterface dtoInterface) {
        try {
            UserDTO userDTO = (UserDTO) dtoInterface;
            User user = (new User())
                    .setFirstName(userDTO.getFirstName())
                    .setLastName(userDTO.getLastName())
                    .setEmail(userDTO.getEmail())
                    .setPhoneNumber(userDTO.getPhoneNumber())
                    .setStatus(userDTO.isStatus());
            userRepository.save(user);

            List<AddressDTO> addressDTOList = new ArrayList<>();
            userDTO.getAddress().forEach(item ->  {
                    item.setUserId(user.getId());
                    addressDTOList.add((AddressDTO) addressService.create(item));
                }
            );

            userDTO.setId(user.getId());
            if (addressDTOList.size() > 0) {
                userDTO.setAddress(addressDTOList);
            }

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

    // TODO change
    public Map<String, List<DTOInterface>> getAllWithPagination(PaginationRequest paginationRequest) {
        Pageable pageable = PageRequest.of(
                paginationRequest.getPage(),
                paginationRequest.getSize(),
                Sort.by("id").descending()
        );

        Page<User> userPage = userRepository.findAll(pageable);
        if (!userPage.hasContent()) {
            return null;
        }

        List<DTOInterface> userDTOList = new ArrayList<>();
        List<DTOInterface> paginationDTOList = new ArrayList<>();
        userPage.getContent().forEach(
                item -> userDTOList.add(userAggregator.prepareDTOByEntity(item))
        );

        paginationDTOList.add(userAggregator.preparePaginationDTOByPage(userPage));
        Map<String, List<DTOInterface>> responseMap = new HashMap<>();
        responseMap.put("data", userDTOList);
        responseMap.put("pagination", paginationDTOList);

        return responseMap;
    }

    @Override
    public void deleteById(Long id) {
        userRepository.delete(
                userRepository.findById(id).stream().findFirst()
                        .orElseThrow(() -> new RecordNotFoundException("User not found"))
        );
    }

    @Override
    public DTOInterface getById(Long id) {
        return userAggregator.prepareDTOByEntity(
                userRepository.findById(id).stream().findFirst()
                        .orElseThrow(() -> new RecordNotFoundException("User not found"))
        );
    }
}
