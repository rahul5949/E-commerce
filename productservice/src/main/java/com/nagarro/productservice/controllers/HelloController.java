package com.nagarro.productservice.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//This class will above multiple methods


@RestController
@RequestMapping("/hello")
public class HelloController {
    @GetMapping("/say")
    public String sayHello(){
        return "Hello There";
    }


}
