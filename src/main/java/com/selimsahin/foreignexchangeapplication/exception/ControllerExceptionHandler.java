package com.selimsahin.foreignexchangeapplication.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.selimsahin.foreignexchangeapplication.response.BaseResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.lang.reflect.UndeclaredThrowableException;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception ex) {
        BaseResponse result;
        HttpStatus httpStatus = HttpStatus.FORBIDDEN;

        if (ex instanceof UndeclaredThrowableException) {
            ex = (Exception) ex.getCause();
        }

        if (ex instanceof CloudException) {
            result = new BaseResponse(((CloudException) ex).exceptionCode(), ex.getMessage(), ex.getMessage());
        } else if (ex instanceof IllegalArgumentException || ex instanceof HttpMessageNotReadableException) {
            httpStatus = HttpStatus.BAD_REQUEST;
            result = new BaseResponse(HttpExceptionEnum.HTTP_INVALID_PARAMETER, ex.getMessage());
        } else if (ex instanceof BindException || ex instanceof MethodArgumentNotValidException) {
            httpStatus = HttpStatus.BAD_REQUEST;

            BindingResult bindingResult = ex instanceof BindException
                    ? (BindingResult) ex : ((MethodArgumentNotValidException) ex).getBindingResult();
            FieldError error = bindingResult.getFieldError();
            String helpMsg = error.getField() + " " + error.getDefaultMessage();
            result = new BaseResponse(HttpExceptionEnum.HTTP_INVALID_PARAMETER, helpMsg);
        } else {
            result = new BaseResponse(HttpExceptionEnum.HTTP_OPERATION_FAILED, ex.getMessage());
        }

        JsonNode response = convertBean2Json(result);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<Object>(convertBean2Json(response), headers, httpStatus);
    }

    public static JsonNode convertBean2Json(Object bean) {
        ObjectMapper MAPPER = new ObjectMapper();
        if (null == bean) {
            return null;
        } else {
            MAPPER.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
            try {
                return MAPPER.convertValue(bean, JsonNode.class);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

}
