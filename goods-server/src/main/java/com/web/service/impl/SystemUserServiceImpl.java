package com.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.web.entity.SystemUser;
import com.web.mapper.SystemUserMapper;
import com.web.service.SystemUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SystemUserServiceImpl implements SystemUserService {

    @Resource
    private SystemUserMapper userMapper;

    @Override
    public SystemUser getUserByNameAndPwd(SystemUser systemUser) {
        QueryWrapper<SystemUser> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("system_name",systemUser.getSystemName());
        queryWrapper.eq("password_login",systemUser.getPasswordLogin());
        queryWrapper.select("id,system_name,code,status,add_time,remark,nick_name,imag_url");
        return userMapper.selectOne(queryWrapper);
    }
}
