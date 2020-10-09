package com.web.controller.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.web.base.BaseController;
import com.web.entity.Goods;
import com.web.entity.GoodsType;
import com.web.service.GoodsTypeService;
import com.web.util.DateUtil;
import com.web.util.General;
import com.web.util.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("goodsTypeApi")
public class GoodsTypeApi extends BaseController {
    @Resource
    private GoodsTypeService goodsTypeService;

    @PostMapping("goodsTypeListPage")
    public IPage<GoodsType> goodsTypeListPage(String dataArray) throws UnsupportedEncodingException {
        Page<GoodsType> goodsTypePage = new Page<GoodsType>();
        JSONObject jsonObject = JSON.parseObject(dataArray);
        goodsTypePage.setSize(Integer.valueOf(jsonObject.get("pagesize").toString()));
        goodsTypePage.setCurrent(Long.valueOf(Integer.valueOf(jsonObject.get("page").toString())));
        String extra_search = jsonObject.get("extra_search") == null ? "" : jsonObject.get("extra_search").toString();
        extra_search = URLDecoder.decode(extra_search, "UTF-8");
        Integer status=1;
        IPage<GoodsType> GoodsTypeListPage = goodsTypeService.GoodsTypeListPage(goodsTypePage,status,extra_search);
        return GoodsTypeListPage;
    }

    @PostMapping("addGoodsType")
    public ResponseEntity<?> addGoodsType(GoodsType goodsType){
        goodsType.setAddTime(DateUtil.getTime());
        boolean flag=goodsTypeService.insertGoodsType(goodsType);
        if (flag){
            return new ResponseEntity<>().setState(General.SUCCESS).setMsg("新增成功");
        }else {
            return new ResponseEntity<>().setState(General.ERROR_1001).setMsg("新增失败");
        }
    }

    @PostMapping("ClassificationNameValid")
    public Map<String,Boolean> ClassificationNameValid(String ClassificationName, Integer id){
        ClassificationName = ClassificationName == null ? ClassificationName : ClassificationName.trim();
        GoodsType goodsType=new GoodsType();
        boolean result=true;
        if (id!=null && !id.equals("")){
            goodsType.setId(id);
        }
        goodsType.setClassificationName(ClassificationName);
        Map<String, Boolean> map = new HashMap<>();
        try {
            int count = goodsTypeService.getGoodsTypeNameValid(goodsType);
            if (count > 0) {
                result = false;
            }
            map.put("valid", result);
        } catch (Exception e) {
        }
        return map;
    }

    @PostMapping("delGoodsType")
    public ResponseEntity<?> delGoodsType(Integer id){
        boolean flag=goodsTypeService.delGoodsType(id);
        if (flag){
            return new ResponseEntity<>().setState(General.SUCCESS).setMsg("删除成功");
        }else {
            return new ResponseEntity<>().setMsg("删除失败").setState(General.ERROR_1001);
        }
    }

}
