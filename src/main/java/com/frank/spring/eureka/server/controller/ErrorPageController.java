package com.frank.spring.eureka.server.controller;

import com.frank.spring.eureka.server.util.RequestHelper;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author IChen.Chu
 * @Date 2023/03/17
 */
@RestController
@RequestMapping("/error")
public class ErrorPageController  {

    @RequestMapping("/999")
    public ResponseEntity<?> toPage999() {
        HttpStatus status = HttpStatus.valueOf(404);
        return RequestHelper.formatError(status.value(), status.getReasonPhrase());
    }

    @RequestMapping("/404")
    public ResponseEntity<?> toPage404() {
        HttpStatus status = HttpStatus.valueOf(404);
        return RequestHelper.formatError(status.value(), status.getReasonPhrase());
    }

    @RequestMapping("/500")
    public ResponseEntity<?> toPage500() {
        HttpStatus status = HttpStatus.valueOf(500);
        return RequestHelper.formatError(status.value(), status.getReasonPhrase());
    }

//    @Override
//    public String getErrorPath() {
//        return "/error/404";
//    }
}
