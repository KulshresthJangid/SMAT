package com.nerdyGeek.smat.dto;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class APIResponseDTO<T> {
    public HttpStatus httpStatus;
    public String message;
    public T body;
}
