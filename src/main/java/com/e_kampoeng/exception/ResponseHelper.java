package com.e_kampoeng.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseHelper {
    public static <T> CommonResponse<T> ok(T data) {
        CommonResponse<T> response = new CommonResponse<T>();

        response.setMessage("Success");
        response.setStatus("200");
        response.setData(data);

        return response;
    }

    public static <T> ResponseEntity<CommonResponse<T>> error(String error, HttpStatus httpStatus) {
        CommonResponse<T> response = new CommonResponse<>();
        response.setStatus(String.valueOf(httpStatus.value()));
        response.setMessage(httpStatus.name());
        response.setData((T) error);
        return new ResponseEntity<>(response, httpStatus);
    }
}
