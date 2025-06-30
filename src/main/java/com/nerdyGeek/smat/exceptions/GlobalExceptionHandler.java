package com.nerdyGeek.smat.exceptions;

import com.nerdyGeek.smat.dto.APIResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleException(Exception e) {
        return ResponseEntity.internalServerError().body(new APIResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR, "Unexpected Error", e.getMessage()));
    }
}
