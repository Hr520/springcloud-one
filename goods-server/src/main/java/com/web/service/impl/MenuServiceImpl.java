package com.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.web.entity.Menu;
import com.web.mapper.MenuMapper;
import com.web.service.MenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class MenuServiceImpl implements MenuService {
    @Resource
    private MenuMapper menuMapper;

    @Override
    public List<Menu> getMenuInId(List<Integer> collect) {
        QueryWrapper<Menu> queryWrapper=new QueryWrapper<>();
        queryWrapper.in("id",collect);
        return menuMapper.selectList(queryWrapper);
    }

    @Override
    public Menu getMenuByUrl(String url) {
        QueryWrapper<Menu> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("menu_url",url);
        return menuMapper.selectOne(queryWrapper);
    }
}
