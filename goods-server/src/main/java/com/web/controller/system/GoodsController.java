package com.web.controller.system;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.web.base.BaseController;
import com.web.entity.GoodsType;
import com.web.service.GoodsService;
import com.web.service.GoodsTypeService;
import com.web.util.General;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("goods")
public class GoodsController extends BaseController {
    @Resource
    private GoodsService goodsService;
    @Resource
    private GoodsTypeService goodsTypeService;

    @GetMapping("JumpGoodsList")
    public String goodsList(Model model){
        saveMenuUrl("goods/JumpGoodsList",getRequest());
        return "/view/goods/goodsList";
    }

    @GetMapping("jumpAddGoods")
    public String jumpAddGoods(Model model){
        saveMenuUrl("goods/JumpGoodsList",getRequest());
        Integer status= General.STATUS_EFFECTIVE;
        List<GoodsType> goodsTypes=goodsTypeService.GoodsTypeList(status);
        if (goodsTypes.size()==0){
            //追加避免下拉为空
            GoodsType goodsType=new GoodsType();
            goodsType.setId(-1);
            goodsType.setClassificationName("暂无类型");
            goodsType.setStatus(1);
            goodsTypes.add(goodsType);
        }
        model.addAttribute("goodsType",goodsTypes);
        return "/view/goods/addGoods";
    }

}
