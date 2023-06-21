package com.yumi.springboot;

import com.alibaba.fastjson2.JSON;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Slf4j
public class MyController {

    @PostMapping("/test")
    void post(HttpServletRequest request, HttpServletResponse response) {
        log.info("queryStr: {}", request.getQueryString());
        log.info("map: {}", JSON.toJSONString(request.getParameterMap()));
    }

    @GetMapping("/get")
    void get(HttpServletRequest request, HttpServletResponse response) {
        log.info("queryStr: {}", request.getQueryString());
        log.info("map: {}", JSON.toJSONString(request.getParameterMap()));
    }

    @ResponseBody
    @PostMapping("/upper")
    String upper(@RequestBody Req req) {
        return req.toString().toUpperCase();
    }
}
