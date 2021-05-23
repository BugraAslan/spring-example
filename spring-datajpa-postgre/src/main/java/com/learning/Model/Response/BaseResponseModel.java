package com.learning.Model.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class BaseResponseModel {

    private boolean success;
    private List<Object> data;
    private String error;
    private int statusCode;
}
