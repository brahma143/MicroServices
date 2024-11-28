package com.microservices.Exception;

import com.microservices.DTO.ErrorResponseDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

   // @Override
    protected ResponseEntity<Object> handleMethodNotValid(MethodArgumentNotValidException exception,
                                                          HttpHeaders headers, HttpStatus status, WebRequest webRequest){
        Map<String, String> validationErrors = new HashMap<>();
        List<ObjectError> validationErrorList =exception.getBindingResult().getAllErrors();

        validationErrorList.forEach((error)->{
            String fieldName= ((FieldError)error).getField();
            String validationMsg= error.getDefaultMessage();
            validationErrors.put(fieldName,validationMsg);
        });
        return new ResponseEntity<>(validationErrors,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> globalExceptioHandler(Exception exception, WebRequest webRequest){

        ErrorResponseDto responseDto = new ErrorResponseDto(
        webRequest.getDescription(false),
        HttpStatus.INTERNAL_SERVER_ERROR,
        exception.getMessage(),
        LocalDateTime.now()
        );

        return new ResponseEntity<>(responseDto,HttpStatus.INTERNAL_SERVER_ERROR);
    }
       @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleResourceNotFoundException(ResourceNotFoundException exception,
                                                                            WebRequest webRequest){
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                webRequest.getDescription(false),
                HttpStatus.NOT_FOUND,
                exception.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponseDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CustomerAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDto> handleCustomerAlreadyException(CustomerAlreadyExistsException exception,
                                                                           WebRequest webRequest){

        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                webRequest.getDescription(false),
                HttpStatus.BAD_GATEWAY,
                exception.getMessage(),
                LocalDateTime.now()
        );

        return new ResponseEntity(errorResponseDto,HttpStatus.BAD_GATEWAY);

    }
}
