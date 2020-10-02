package com.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.web.entity.Goods;
import com.web.mapper.GoodsMapper;
import com.web.service.GoodsService;
import com.web.util.rBool;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {
    @Resource
    private GoodsMapper goodsMapper;

    @Override
    public boolean addGoods(Goods goods) {
         boolean flg= rBool.ChangeIntToBoolean(goodsMapper.insert(goods)) ;
        return flg;
    }

    @Override
    public List<Goods> GoodsList() {
        return goodsMapper.selectList(null);
    }

    @Override
    public IPage<Goods> GoodsListPage(Page<Goods> goodsPage,Integer state,String GoodsName) {
        return goodsMapper.selectPageVo(goodsPage,state,GoodsName);
    }

    @Override
    public int getGoodsNameValid(Goods goods) {
        QueryWrapper<Goods> goodsQueryWrapper=new QueryWrapper<>();
        goodsQueryWrapper.eq("goods_name",goods.getGoodsName());
        if (goods.getUuid()!=null && !goods.getUuid().equals("")){
            goodsQueryWrapper.ne("uuid",goods.getUuid());
        }
        return goodsMapper.selectCount(goodsQueryWrapper);
    }

    @Override
    public boolean deleteGoods(Long uuid) {
        QueryWrapper<Goods> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("uuid",uuid);
        return rBool.ChangeIntToBoolean(goodsMapper.delete(queryWrapper));
    }

    @Override
    public int getCoding(Goods goods) {
        QueryWrapper<Goods> queryWrapper=new QueryWrapper<>();
        if (goods.getUuid()!=null && !goods.getUuid().equals("")){
            queryWrapper.ne("uuid",goods.getUuid());
        }
        queryWrapper.eq("coding",goods.getCoding());
        return goodsMapper.selectCount(queryWrapper);
    }
}
