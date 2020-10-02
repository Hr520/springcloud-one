package com.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.web.entity.UserRole;
import com.web.mapper.UserRoleMapper;
import com.web.service.UserRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserRoleServiceImpl implements UserRoleService {
    @Resource
    private UserRoleMapper userRoleMapper;
    @Override
    public List<UserRole> getUserRoleListByUserId(Integer id) {
        QueryWrapper<UserRole> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("user_id",id);
        return userRoleMapper.selectList(queryWrapper);
    }
}
