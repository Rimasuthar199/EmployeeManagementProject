package com.example.employee;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {



    @GetMapping("/test")
    public String getMessage(){
        return  "Ok Working!!!!";
    }
}
