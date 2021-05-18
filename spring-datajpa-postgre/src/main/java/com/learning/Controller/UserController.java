package com.learning.Controller;

import com.learning.Aggregator.UserAggregator;
import com.learning.DTO.DTOInterface;
import com.learning.DTO.UserDTO;
import com.learning.Manager.Response.UserResponseManager;
import com.learning.Model.Request.UserRequest;
import com.learning.Model.Response.BaseResponseModel;
import com.learning.Model.Response.Response;
import com.learning.Model.Response.UserResponse;
import com.learning.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
        DTOInterface user = userService.getById(id);
        if (user == null) {
            return response.customErrorResponse("Kullanıcı Bulunamadı", HttpStatus.NOT_FOUND);
        }

        return response.successResponse(
                userResponseManager.buildUserResponse((UserDTO) user)
        );
    }

    @PatchMapping()
    public BaseResponseModel updateUser(@RequestBody UserRequest userRequest) {
        DTOInterface user = userService.update(userAggregator.prepareDTOByRequest(userRequest));
        if (user == null) {
            return response.notAcceptableResponse("Kullanıcı Güncellenemedi");
        }

        return response.successResponse(
                userResponseManager.buildUserResponse((UserDTO) user)
        );
    }

    @PostMapping()
    public BaseResponseModel createUser(@RequestBody UserRequest userRequest) {
        DTOInterface user = userService.create(userAggregator.prepareDTOByRequest(userRequest));
        if (user == null) {
            return response.notAcceptableResponse("Kullanıcı Güncellenemedi");
        }

        return response.successResponse(
                userResponseManager.buildUserResponse((UserDTO) user)
        );
    }

    @GetMapping("/list")
    public BaseResponseModel getAllUser() {
        List<UserResponse> userResponseList = new ArrayList<>();
        userService.getAll().forEach(
                userDto -> userResponseList.add(userResponseManager.buildUserResponse((UserDTO) userDto))
        );

        return response.successResponse(userResponseList);
    }
}
