package com.example.demo.base;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/error")
public class ErrorController extends BaseController {

    @RequestMapping(value = "/404")
    public ResponseData error404() {
        return failureData("404 error,not found");
    }

    @RequestMapping(value = "/500")
    public ResponseData error500() {
        return failureData("500 error,internal server error");
    }

    @RequestMapping(value = "/test/500")
    public ResponseData test500() {
        int i = 1/0;
        return successData("500 error");
    }
}
