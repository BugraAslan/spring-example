package com.learning.Model.Response;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Response {

    public BaseResponseModel successResponse(List<Object> data) {
        return new BaseResponseModel(
                true,
                data,
                null,
                HttpStatus.OK
        );
    }

    public BaseResponseModel successResponse(Object data) {
        List<Object> objects = new ArrayList<>();
        objects.add(data);

        return new BaseResponseModel(
                true,
                objects,
                null,
                HttpStatus.OK
        );
    }

    public BaseResponseModel notAcceptableResponse(String error) {
        return new BaseResponseModel(
                false,
                null,
                error,
                HttpStatus.NOT_ACCEPTABLE
        );
    }

    public BaseResponseModel customErrorResponse(String error, HttpStatus httpStatus) {
        return new BaseResponseModel(
                false,
                null,
                error,
                httpStatus
        );
    }

    public BaseResponseModel customErrorResponse(List<String> errors, HttpStatus httpStatus) {
        return new BaseResponseModel(
                false,
                null,
                String.join(" | ", errors),
                httpStatus
        );
    }
}
