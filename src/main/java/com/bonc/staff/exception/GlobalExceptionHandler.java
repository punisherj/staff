package com.bonc.staff.exception;


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

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrorResponse handleBusinessException(BusinessException ex) {
        return new ApiErrorResponse(400, 4001, ex.getMessage());
    }

    /**
     * 用于处理校验参数 @valid
     * @param ex
     * @return
     */
    @ExceptionHandler(value = { IllegalArgumentException.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrorResponse IllegalArgumentException(IllegalArgumentException ex) {
        return new ApiErrorResponse(501, 5002, ex.getMessage());
    }

    /**
     * 找不到页面的异常处理 需要配置application.yml
     * Spring Boot中, 当用户访问了一个不存在的链接时,
     * Spring 默认会将页面重定向到 /error**上, 而不会抛出异常. 既然如此, 那我们就告诉 Spring Boot,
     * 当出现 404 错误时, 抛出一个异常即可. 在 application.properties
     * 中添加两个配置: spring.mvc.throw-exception-if-no-handler-found=true
     * spring.resources.add-mappings=false 上面的配置中,
     * 第一个 spring.mvc.throw-exception-if-no-handler-found
     * 告诉 SpringBoot 当出现 404 错误时, 直接抛出异常.
     * 第二个 spring.resources.add-mappings 告诉 SpringBoot不要为我们工程中的资源文件建立映射.
     * @param ex
     * @return
     */
    @ExceptionHandler(NoHandlerFoundException.class)
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
