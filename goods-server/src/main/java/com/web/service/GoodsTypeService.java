package com.web.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.web.entity.GoodsType;

import java.util.List;

public interface GoodsTypeService {
    List<GoodsType> GoodsTypeList(Integer status);

    IPage<GoodsType> GoodsTypeListPage(Page<GoodsType> goodsTypePage,Integer status,String extra_search);

    boolean insertGoodsType(GoodsType goodsType);

    int getGoodsTypeNameValid(GoodsType goodsType);

    boolean delGoodsType(Integer id);
}
