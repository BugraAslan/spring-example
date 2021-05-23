package com.learning.Service;

import com.learning.Aggregator.UserAggregator;
import com.learning.DTO.AddressDTO;
import com.learning.DTO.DTOInterface;
import com.learning.DTO.UserDTO;
import com.learning.Entity.User;
import com.learning.Model.Request.PaginationRequest;
import com.learning.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserService implements CrudService {

    private final UserRepository userRepository;
    private final UserAggregator userAggregator;
    private final AddressService addressService;

    @Override
    @Transactional
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
                System.out.println("girdi");
                    item.setUserId(user.getId());
                    addressDTOList.add((AddressDTO)addressService.create(item));
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
}
