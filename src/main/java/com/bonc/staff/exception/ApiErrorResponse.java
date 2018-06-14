package com.bonc.staff.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * @author xukj
 */
@Data
@AllArgsConstructor
@ToString
public class ApiErrorResponse {
    private int status;
    private int code;
    private String message;
}
