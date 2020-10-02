package com.web.service;

import com.web.entity.Role;

import java.util.List;

public interface RoleService {
    boolean insertRole(Role role);

    List<Role> selectRoleList(Integer id);

    List<Role> roleList(Integer userId);
}
