package com.frank.spring.eureka.server.controller;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author IChen.Chu
 * @Date 2023/03/15
 */
@Slf4j
@Controller
public class BasicErrorController implements ErrorController {
    private static final String PATH = "/error";

    private static final String ERROR_FLAG = "errors";

    @Autowired
    private ErrorAttributes errorAttributes;

    public String getErrorPath() {
        return PATH;
    }

    @RequestMapping
    @ResponseBody
    public JSONObject doHandleError(HttpServletRequest request) {
        JSONObject reData = new JSONObject();
        Integer status = null;
        Object message = null;
        try {
            WebRequest webRequest = new ServletWebRequest(request);
//            Map<String, Object> errorAttributesData = errorAttributes.getErrorAttributes(webRequest, false);
            Map<String, Object> errorAttributesData = errorAttributes.getErrorAttributes(webRequest, ErrorAttributeOptions.defaults());
            status = (Integer) errorAttributesData.get("status");
            if (errorAttributesData.containsKey(ERROR_FLAG)) {
                List<ObjectError> errorMap = (List<ObjectError>) errorAttributesData.get("errors");
                List<String> errors = new ArrayList<>();
                for (ObjectError error : errorMap) {
                    FieldError errorField = (FieldError) (error);
                    String str = errorField.getField() + " " + errorField.getDefaultMessage();
                    errors.add(str);
                }
                if (errors.size() == 1) {
                    String error = errors.get(0);
                    message = error;
                } else {
                    message = errors;
                }
            } else {
                String error = (String) errorAttributesData.get("message");
                int index = error.indexOf(":");
                if (index != -1 && index > 0) {
                    error = error.substring(0, index);
                }
                message = error;
            }
        } catch (Exception ex) {
            log.info("Exception Handle error:" + ex.getMessage());
        }
        reData.put("errCode", status);
        reData.put("errMsg", message);
        return reData;
    }
}
