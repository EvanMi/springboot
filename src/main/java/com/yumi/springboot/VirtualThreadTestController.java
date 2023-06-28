package com.yumi.springboot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@Slf4j
@RequestMapping
@RestController
public class VirtualThreadTestController {

    @GetMapping("/virtual")
    public String virtual() {
        try {
            TimeUnit.MILLISECONDS.sleep(300);
        } catch (Exception e) {
            //ignore
        }
        return "Current Thread Name: " + Thread.currentThread();
    }
}
