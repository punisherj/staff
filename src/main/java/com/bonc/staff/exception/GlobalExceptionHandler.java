package com.bonc.staff.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 *
 * @author xukj 定义全局异常处理
 * @RestControllerAdvice 是@controlleradvice 与@ResponseBody 的组合注解
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrorResponse handleBusinessException(BusinessException ex) {
        return new ApiErrorResponse(400, 4001, ex.getMessage());
    }

    //@ExceptionHandler(value = { IllegalArgumentException.class })
    //@ResponseStatus(HttpStatus.BAD_REQUEST)
    //public ApiErrorResponse IllegalArgumentException(IllegalArgumentException ex) {
    //    return new ApiErrorResponse(501, 5002, ex.getMessage());
    //}

    //@ExceptionHandler(value = { NoHandlerFoundException.class })
    //@ResponseStatus(HttpStatus.NOT_FOUND)
    //public ApiErrorResponse noHandlerFoundException(Exception ex) {
    //    return new ApiErrorResponse(404, 4041, ex.getMessage());
    //}


    @ExceptionHandler(value = { Exception.class })
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiErrorResponse unknownException(Exception ex) {
        return new ApiErrorResponse(500, 5002, ex.getMessage());
    }
}
