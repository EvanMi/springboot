package com.yumi.springboot;

import com.alibaba.fastjson2.JSON;
import com.yumi.springboot.notfound.AnotherNotFoundException;
import com.yumi.springboot.notfound.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@Slf4j
public class NotFoundController {
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Result {
        private String name;
        private int age;
    }

    private static final Result SUCCESS_RES = new Result("nani", 12);

    @GetMapping("/rnfe/{id}")
    public Result rnfe(@PathVariable("id") long id) {
        log.info("id: {}", id);
        if (id <= 0) {
            throw new ResourceNotFoundException();
        }
        return SUCCESS_RES;
    }


    @GetMapping("/rse/{id}")
    public Result rse(@PathVariable("id") long id){
        log.info("id: {}", id);
        if (id <= 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return SUCCESS_RES;
    }

    @GetMapping("/anfe/{id}")
    public Result anfe(@PathVariable("id") long id){
        log.info("id: {}", id);
        if (id <= 0) {
            throw new AnotherNotFoundException();
        }
        return SUCCESS_RES;
    }

    @ExceptionHandler(AnotherNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Result handleException(AnotherNotFoundException ex) {
        log.info("exception handled : {}", ex.getClass().getName());
        return new Result("not-found", 22);
    }


    @GetMapping("/re/{id}")
    public ResponseEntity<Result> re(@PathVariable("id") long id) {
        log.info("id: {}", id);
        if (id <= 0) {
            return ResponseEntity.status(404).body(new Result("not-found", 22));
           //return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(SUCCESS_RES);
    }

    @SneakyThrows
    @GetMapping("resp/{id}")
    public void resp(@PathVariable("id") long id, HttpServletResponse resp) {
        log.info("id: {}", id);
        if (id <= 0) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            resp.getWriter().print(JSON.toJSONString(SUCCESS_RES));
            return;
        }
        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().print(JSON.toJSONString(SUCCESS_RES));
    }
}
