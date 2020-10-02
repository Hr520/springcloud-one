package com.web.service;

import com.web.entity.UserRole;

import java.util.List;

public interface UserRoleService {
    List<UserRole> getUserRoleListByUserId(Integer id);
}
