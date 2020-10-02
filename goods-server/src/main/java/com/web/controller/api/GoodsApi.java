package com.web.controller.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.web.base.BaseController;
import com.web.entity.Goods;
import com.web.service.GoodsService;
import com.web.util.DateUtil;
import com.web.util.General;
import com.web.util.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "goodsApi")
public class GoodsApi extends BaseController {
    @Autowired
    private GoodsService goodsService;

    @PostMapping("addGoods")
    public ResponseEntity<?> addGoods(Goods goods){
        goods.setUuid(idWorker.nextId());
        goods.setAddDate(DateUtil.getDay());
        String time=DateUtil.getTime();
        goods.setAddTime(time);
        goods.setUpdateTime(time);
        if (goods.getExpiredStatus()==null){
            long validTimeUnix=DateUtil.changeTimeToUNIX(goods.getValidTime()+" 00:00:01");
            long NowTimeUnix=DateUtil.getNowUnixTime();
            if (validTimeUnix>NowTimeUnix){
                goods.setExpiredStatus(1);
            }
        }
        if (goods.getSort()==null) goods.setSort(1);
        boolean flag=goodsService.addGoods(goods);
        if (flag){
            return new ResponseEntity<>().setState(General.SUCCESS).setMsg("新增成功");
        }else {
            return new ResponseEntity<>().setState(General.ERROR_0000).setMsg("新增失败");
        }
    }

    @PostMapping("GoodsList")
    public ResponseEntity<?> GoodsList(){
        List<Goods> goodsList=goodsService.GoodsList();
        return new ResponseEntity<>().setData(goodsList).setState(General.SUCCESS).setMsg("数据获取成功");
    }

    @PostMapping("GoodsNameValid")
    public Map<String,Boolean> GoodsNameValid(String GoodsName,Long Id){
        GoodsName = GoodsName == null ? GoodsName : GoodsName.trim();
        Goods goods=new Goods();
        boolean result = true;
        if (Id !=null && !Id.equals("")){
            goods.setUuid(Id);
        }
        goods.setGoodsName(GoodsName);
        Map<String, Boolean> map = new HashMap<>();
        try {
            int count = goodsService.getGoodsNameValid(goods);
            if (count > 0) {
                result = false;
            }
            map.put("valid", result);
        } catch (Exception e) {
        }
        return map;
    }

    @PostMapping("GoodsCodingValid")
    public Map<String,Boolean> GoodsCoding(String Coding,Long uuid){
        Coding = Coding == null ? Coding : Coding.trim();
        Goods goods=new Goods();
        boolean result = true;
        if (uuid !=null && !uuid.equals("")){
            goods.setUuid(uuid);
        }
        goods.setCoding(Coding);
        Map<String, Boolean> map = new HashMap<>();
        try {
            int count = goodsService.getCoding(goods);
            if (count > 0) {
                result = false;
            }
            map.put("valid", result);
        } catch (Exception e) {
        }
        return map;
    }

    @PostMapping("GoodsListPage")
    public IPage<Goods> GoodsListPage(String dataArray) throws UnsupportedEncodingException {
        Page<Goods> goodsPage=new Page<Goods>();
        JSONObject jsonObject=JSON.parseObject(dataArray);
        goodsPage.setSize(Integer.valueOf(jsonObject.get("pagesize").toString()));
        goodsPage.setCurrent(Long.valueOf(Integer.valueOf(jsonObject.get("page").toString())));
        Integer state=1;
        String extra_search=jsonObject.get("extra_search")==null ? "":jsonObject.get("extra_search").toString();
        extra_search = URLDecoder.decode(extra_search, "UTF-8");

        IPage<Goods> GoodsListPage = goodsService.GoodsListPage(goodsPage, state,extra_search);
        return GoodsListPage;
    }

    @PostMapping("deleteGoods")
    public  ResponseEntity<?> deleteGoods(String uuid){
        boolean flag=goodsService.deleteGoods(Long.parseLong(uuid));
        if (flag){
            return new ResponseEntity<>().setState(General.SUCCESS).setMsg("删除成功");
        }else {
            return new ResponseEntity<>().setState(General.ERROR_1001).setState("删除失败");
        }
    }
}
