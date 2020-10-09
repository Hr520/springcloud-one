package com.web.controller.system;

import com.web.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("goodsType")
public class GoodsTypeController  extends BaseController {

    @GetMapping("JumpGoodsTypeList")
    public String goodsTypeList(Model model){
        saveMenuUrl("goodsType/JumpGoodsTypeList",getRequest());
        return "/view/goodsType/goodsTypeList";
    }

    @GetMapping("JumpAddGoodsType")
    public String JumpAddGoodsType(Model model){
        saveMenuUrl("goodsType/JumpGoodsTypeList",getRequest());
        return "/view/goodsType/addGoodsType";
    }
}
