package com.data.penduduk.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseHelper {

    public static <T> CustomResponse<T> ok(T data) {
        CustomResponse<T> response = new CustomResponse<T>();
        response.setMessage("Success");
        response.setStatus("200");
        response.setData(data);
        return response;
    }

    public static <T> PaginationResponse<T> okWithPagination(T data, Object pagination) {
        PaginationResponse<T> response = new PaginationResponse<T>();
        response.setStatus("200 OK");
        response.setCode(200);
        response.setData(data);
        response.setMessage("success");
        response.setPagination(pagination);
        return response;
    }

    public static <T> ResponseEntity<CustomResponse<T>> error(String eror, HttpStatus httpStatus) {
        CustomResponse<T> response = new CustomResponse<>();
        response.setStatus(String.valueOf(httpStatus.value()));
        response.setMessage(httpStatus.name());
        response.setData((T) eror);
        return new ResponseEntity<>(response, httpStatus);
    }
}
