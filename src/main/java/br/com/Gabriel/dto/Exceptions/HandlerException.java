package br.com.Gabriel.dto.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class HandlerException extends RuntimeException {
    public HandlerException(String message) {
        super(message);
    }
}
