package com.web.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.web.entity.GoodsType;
import com.web.mapper.GoodsTypeMapper;
import com.web.service.GoodsTypeService;
import com.web.util.rBool;
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

    @Override
    public IPage<GoodsType> GoodsTypeListPage(Page<GoodsType> goodsTypePage,Integer status,String extra_search) {
        return goodsTypeMapper.selectPageVo(goodsTypePage,status,extra_search);
    }

    @Override
    public boolean insertGoodsType(GoodsType goodsType) {
        return rBool.ChangeIntToBoolean(goodsTypeMapper.insert(goodsType));
    }

    @Override
    public int getGoodsTypeNameValid(GoodsType goodsType) {
        QueryWrapper<GoodsType> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("classification_name",goodsType.getClassificationName());
        if (goodsType.getId() !=null && !goodsType.getId().equals("")){
            queryWrapper.ne("id",goodsType.getId());
        }
        return goodsTypeMapper.selectCount(queryWrapper);
    }

    @Override
    public boolean delGoodsType(Integer id) {
        return rBool.ChangeIntToBoolean(goodsTypeMapper.deleteById(id));
    }
}
