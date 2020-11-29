
package com.europeandynamics.exceptions;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class MyExceptionHandler {
    
    Date myDate = new Date();

    @ExceptionHandler(value = {ApiException.class})
    public ResponseEntity<Object> HandleUserNotFoundException(ApiException exception) {
        
        CustomExceptionDetails errorDetails = new CustomExceptionDetails(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(myDate), exception.getMessage());

        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }
}
