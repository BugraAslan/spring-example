package com.learning.Controller;

import com.learning.Aggregator.UserAggregator;
import com.learning.DTO.DTOInterface;
import com.learning.DTO.PaginationDTO;
import com.learning.DTO.UserDTO;
import com.learning.Exception.RecordNotFoundException;
import com.learning.Manager.Response.UserResponseManager;
import com.learning.Model.Request.PaginationRequest;
import com.learning.Model.Request.UserRequest;
import com.learning.Model.Response.BaseResponseModel;
import com.learning.Model.Response.Response;
import com.learning.Model.Response.UserResponse;
import com.learning.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserResponseManager userResponseManager;
    private final Response response;
    private final UserAggregator userAggregator;

    @GetMapping("/{id}")
    public BaseResponseModel getUserById(@PathVariable Long id) {
        return response.successResponse(
                userResponseManager.buildUserResponse((UserDTO) userService.getById(id))
        );
    }

    @PatchMapping()
    public BaseResponseModel updateUser(@RequestBody UserRequest userRequest) {
        return response.successResponse(
                userResponseManager.buildUserResponse(
                        (UserDTO) userService.update(userAggregator.prepareDTOByRequest(userRequest))
                )
        );
    }

    @DeleteMapping("/{id}")
    public BaseResponseModel deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
        return response.successResponse("Deleted");
    }

    @PostMapping()
    public BaseResponseModel createUser(@RequestBody UserRequest userRequest) {
        return response.successResponse(
                userResponseManager.buildUserResponse(
                        (UserDTO) userService.create(userAggregator.prepareDTOByRequest(userRequest))
                )
        );
    }

    @GetMapping("/list")
    public BaseResponseModel getAllUser() {
        List<UserResponse> userResponseList = new ArrayList<>();
        userService.getAll().forEach(
                userDto -> userResponseList.add(userResponseManager.buildUserResponse((UserDTO) userDto))
        );

        if (userResponseList.isEmpty()) {
            throw new RecordNotFoundException("User not found");
        }

        return response.successResponse(userResponseList);
    }

    @GetMapping("/list/pagination")
    // TODO change
    public BaseResponseModel getAllUserWithPagination(@RequestBody PaginationRequest paginationRequest) {
        try {
            Map<String, List<DTOInterface>> userList = userService.getAllWithPagination(paginationRequest);
            List<Object> userResponseList = new ArrayList<>();
            userList.get("data").forEach(
                    userDto -> userResponseList.add(userResponseManager.buildUserResponse((UserDTO) userDto))
            );
            userResponseList.add(
                    userResponseManager.buildPaginationResponse(
                            (PaginationDTO) userList.get("pagination").get(0)
                    )
            );

            return response.successResponse(userResponseList);
        } catch (NullPointerException nullPointerException) {
            return response.successResponse();
        }
    }
}
