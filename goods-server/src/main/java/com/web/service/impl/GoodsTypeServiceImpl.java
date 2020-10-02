package com.web.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.web.entity.GoodsType;
import com.web.mapper.GoodsTypeMapper;
import com.web.service.GoodsTypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class GoodsTypeServiceImpl extends ServiceImpl<GoodsTypeMapper, GoodsType> implements GoodsTypeService{

    @Resource
    private GoodsTypeMapper goodsTypeMapper;
    @Override
    public List<GoodsType> GoodsTypeList(Integer status) {
        QueryWrapper<GoodsType> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("status",status);
        return goodsTypeMapper.selectList(queryWrapper);
    }
}
