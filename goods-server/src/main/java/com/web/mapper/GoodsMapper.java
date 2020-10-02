package com.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.web.entity.Goods;

public interface GoodsMapper extends BaseMapper<Goods> {
    IPage<Goods> selectPageVo(Page<Goods> goodsPage, Integer state,String GoodsName);
}
