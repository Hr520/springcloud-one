package com.web.service;

import com.web.entity.SystemUser;

public interface SystemUserService {
    SystemUser getUserByNameAndPwd(SystemUser systemUser);
}
