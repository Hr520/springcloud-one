package com.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.web.entity.GoodsType;

public interface GoodsTypeMapper extends BaseMapper<GoodsType> {
    IPage<GoodsType> selectPageVo(Page<GoodsType> goodsTypePage, Integer status,String ClassificationName);
}
