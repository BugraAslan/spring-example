package com.learning.Handler;

import com.learning.Exception.RecordNotFoundException;
import com.learning.Model.Response.BaseResponseModel;
import com.learning.Model.Response.Response;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler({RecordNotFoundException.class})
    public BaseResponseModel recordNotFoundHandler(RecordNotFoundException exception) {
        return new Response().notAcceptableResponse(exception.getMessage());
    }
}
