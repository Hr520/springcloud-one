package com.web.feign;

import com.sun.org.glassfish.gmbal.ParameterNames;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient("feign-server")
public interface FeignService {

    @GetMapping("/user/getOne")
    String getOne(@RequestParam("name") String name,@RequestParam("age") Integer age);
}
