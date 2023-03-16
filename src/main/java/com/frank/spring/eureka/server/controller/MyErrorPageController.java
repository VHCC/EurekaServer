package com.frank.spring.eureka.server.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author IChen.Chu
 * @Date 2023/03/17
 */
@Slf4j
@Controller
@RequestMapping("${server.error.path:${error.path:/error}}")
public class MyErrorPageController implements ErrorController {



    @RequestMapping(produces = MediaType.ALL_VALUE)
    public ModelAndView errorHtml(HttpServletRequest request, HttpServletResponse response) {
        log.error("Handling error response");
        HttpStatus status = getStatus(request);
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("statusCode", status.value());
        modelAndView.addObject("statusMessage", status.getReasonPhrase());
        modelAndView.addObject("errorCause", getErrorCause(request));
        return modelAndView;
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode != null) {
            try {
                return HttpStatus.valueOf(statusCode);
            }
            catch (Exception ex) {
                log.error("Invalid status code", ex);
            }
        }
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }

    private Throwable getErrorCause(HttpServletRequest request) {
        Throwable error = (Throwable) request.getAttribute("javax.servlet.error.exception");
        if (error != null) {
            while (error.getCause() != null) {
                error = error.getCause();
            }
        }
        return error;
    }
}
