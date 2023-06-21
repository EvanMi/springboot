package com.yumi.springboot.log;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.util.Map;

@Component
@RequestScope
@Data
public class LogEntity {
    private String path;
    private Map<String, String[]> params;
    private Object req;
    private Object resp;
}
