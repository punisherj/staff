package com.bonc.staff.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author xukj
 */
@Data
@Component
@PropertySource(value = "classpath:cookie.properties")
public class CookieConfig {

    @Value("${cookie}")
    private String cookie;
}
