package com.bonc.staff.exception;


import org.apache.http.client.ClientProtocolException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 *
 * @author xukj 定义全局异常处理
 * @RestControllerAdvice 是@controlleradvice 与@ResponseBody 的组合注解
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = { ClientProtocolException.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrorResponse constraintViolationException(ClientProtocolException ex) {
        return new ApiErrorResponse(500, 5001, "cookie过期");
    }

    @ExceptionHandler(value = { IllegalArgumentException.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrorResponse IllegalArgumentException(IllegalArgumentException ex) {
        return new ApiErrorResponse(501, 5002, ex.getMessage());
    }

    @ExceptionHandler(value = { NoHandlerFoundException.class })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrorResponse noHandlerFoundException(Exception ex) {
        return new ApiErrorResponse(404, 4041, ex.getMessage());
    }


    @ExceptionHandler(value = { Exception.class })
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiErrorResponse unknownException(Exception ex) {
        return new ApiErrorResponse(500, 5002, ex.getMessage());
    }
}
