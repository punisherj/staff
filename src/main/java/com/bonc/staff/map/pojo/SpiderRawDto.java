package com.bonc.staff.map.pojo;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xukj
 */
public class SpiderRawDto implements Serializable {
    public static final int COOKIE_INVALID = -98;
    public static final String COOKIE_INVALID_MESSAGE = "cookie失效";
    public static final int IO_EXCEPTION_CODE = -99;
    public static final String IO_EXCEPTION_MESSAGE = "io 异常";
    private int status;
    private String rawContent;
    private Date startTime;
    private Date endTime;
    private Map<String, String> headers = new HashMap();

    public SpiderRawDto() {
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getRawContent() {
        return this.rawContent;
    }

    public void setRawContent(String rawContent) {
        this.rawContent = rawContent;
    }

    public Date getStartTime() {
        return this.startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return this.endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Map<String, String> getHeaders() {
        return this.headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
