package com.web.controller.system;

import com.web.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/gateWay")
public class ErrorController extends BaseController{
    @RequestMapping
    public String overLimitIP(){
        return "/view/overLimitIP";
    }
}
