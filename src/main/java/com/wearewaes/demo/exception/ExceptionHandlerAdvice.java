package com.wearewaes.demo.exception;

import com.wearewaes.demo.dto.ErrorDto;
import com.wearewaes.demo.dto.InputErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Map;

@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(InvalidInputException.class)
    public ResponseEntity<InputErrorDto> processInvalidInputException(HttpServletRequest req, InvalidInputException ex) {

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        int status = HttpStatus.BAD_REQUEST.value();
        String error = HttpStatus.BAD_REQUEST.getReasonPhrase();
        String uri = req.getRequestURI();
        Map<String, String> messages = ex.getMessages();

        InputErrorDto response = new InputErrorDto(timestamp, status, error, uri, messages);


        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorDto> processNotFoundException(HttpServletRequest req, NotFoundException ex) {

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        int status = HttpStatus.NOT_FOUND.value();
        String error = HttpStatus.NOT_FOUND.getReasonPhrase();
        String uri = req.getRequestURI();
        String message = ex.getMessage();

        ErrorDto response = new ErrorDto(timestamp, status, error, uri, message);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorDto> processBadRequestException(HttpServletRequest req, BadRequestException ex) {

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        int status = HttpStatus.BAD_REQUEST.value();
        String error = HttpStatus.BAD_REQUEST.getReasonPhrase();
        String uri = req.getRequestURI();
        String message = ex.getMessage();

        ErrorDto response = new ErrorDto(timestamp, status, error, uri, message);
        return ResponseEntity.badRequest().body(response);
    }

}
