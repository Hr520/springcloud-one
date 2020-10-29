package com.lh.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/getOne")
    public String getOne(@RequestParam("name") String name,Integer age){
        return "name ="+name +"-----"+"age ="+age;
    }
}
