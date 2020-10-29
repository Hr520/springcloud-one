package com.web.controller.api;

import com.web.feign.FeignService;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
@RequestMapping("/cs")
public class CustomerController {

    @Autowired
    private FeignService feignService;

    @RequestMapping(value = "/feign-consumer",method = RequestMethod.GET)
    public String helloConsumer(@RequestParam("name")String name,@RequestParam("age") Integer age){
       return feignService.getOne(name,age);
    }
}
