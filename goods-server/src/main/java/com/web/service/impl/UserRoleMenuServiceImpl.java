package com.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.web.entity.RoleMenu;
import com.web.mapper.UserRoleMenuMapper;
import com.web.service.UserRoleMenuService;
import org.springframework.stereotype.Service;
import vo.MenuVo;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserRoleMenuServiceImpl implements UserRoleMenuService {
    @Resource
    private UserRoleMenuMapper userRoleMenuMapper;
    @Override
    public List<RoleMenu> getUserRoleMenuListInRoleId(List<Integer> roleIdList) {
        QueryWrapper<RoleMenu> queryWrapper=new QueryWrapper<>();
        queryWrapper.in("role_id",roleIdList);
        return userRoleMenuMapper.selectList(queryWrapper);
    }

    @Override
    public List<MenuVo> cacheUserMenu(Integer id) {

        return null;
    }
}
