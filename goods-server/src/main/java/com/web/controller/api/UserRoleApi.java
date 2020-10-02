package com.web.controller.api;

import com.web.base.BaseController;
import com.web.entity.SystemUser;
import com.web.entity.UserRole;
import com.web.service.UserRoleService;
import com.web.util.General;
import com.web.util.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping(value = "userRoleApi")
public class UserRoleApi  extends BaseController {
    @Resource
    private UserRoleService userRoleService;
    @PostMapping("userRoleList")
    public ResponseEntity<?> userRoleList(){
        SystemUser systemUser=(SystemUser) getSession().getAttribute(getSession().getId());
        List<UserRole> list=userRoleService.getUserRoleListByUserId(systemUser.getId());
        return new ResponseEntity<>().setData(list).setState(General.SUCCESS).setMsg("数据获取成功");
    }
}
