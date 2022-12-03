package com.educativa.ova.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class Response {
    private int status;
    private String message;
}
