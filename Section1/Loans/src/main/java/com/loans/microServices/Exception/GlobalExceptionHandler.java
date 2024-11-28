package com.loans.microServices.Exception;

import com.loans.microServices.DTO.ErrorResponseDto;
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

   // @ExceptionHandler("")
    protected ResponseEntity<Object> handlerException(MethodArgumentNotValidException exception,
                                                      HttpHeaders headers, HttpStatus status, WebRequest webRequest) {


        Map<String, String> validationErrors=new HashMap<>();
        List<ObjectError> validationErrorsList =exception.getBindingResult().getAllErrors();

        validationErrorsList.forEach((e)->{
            String fieldName=((FieldError)e).getField();
            String fieldMsg= e.getDefaultMessage();
            validationErrors.put(fieldName, fieldMsg);
        });

        return new ResponseEntity<>(validationErrorsList,HttpStatus.INTERNAL_SERVER_ERROR) ;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> errorResponse(Exception exception,WebRequest webRequest){

        ErrorResponseDto errorResponseDto = new ErrorResponseDto
                (webRequest.getDescription(false),
                 HttpStatus.INTERNAL_SERVER_ERROR,
                        exception.getMessage(),
                        LocalDateTime.now()
                );

        return new ResponseEntity<>(errorResponseDto,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleErrorException(ResourceNotFoundException exception,WebRequest webRequest){

        ErrorResponseDto errorResponseDto = new ErrorResponseDto(webRequest.getDescription(false),
                HttpStatus.NOT_FOUND, exception.getMessage(), LocalDateTime.now());

        return new ResponseEntity<>(errorResponseDto,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(LoanAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDto> userNotFound(LoanAlreadyExistsException exception, WebRequest webRequest){

        ErrorResponseDto errorResponseDto = new ErrorResponseDto(webRequest.getDescription(false), HttpStatus.BAD_GATEWAY, exception.getMessage(),LocalDateTime.now() );

        return new ResponseEntity<>(errorResponseDto,HttpStatus.BAD_GATEWAY);
    }
}
