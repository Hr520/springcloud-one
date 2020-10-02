package com.web.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.web.entity.Goods;

import java.util.List;

public interface GoodsService {

    boolean addGoods(Goods goods);

    List<Goods> GoodsList();

    IPage<Goods> GoodsListPage(Page<Goods> goodsPage, Integer state,String extra_search);

    int getGoodsNameValid(Goods goods);

    boolean deleteGoods(Long uuid);

    int getCoding(Goods goods);
}
