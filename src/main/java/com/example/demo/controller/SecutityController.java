package com.example.demo.controller;

import com.example.demo.security.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecutityController {

    @GetMapping("/security")
    public String display(){
        return  "Spring security called!!";
    }
}
