package com.web.controller.api;

import com.web.base.BaseController;
import com.web.entity.Role;
import com.web.entity.SystemUser;
import com.web.service.RoleService;
import com.web.util.General;
import com.web.util.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping(value = "roleApi")
public class RoleApi extends BaseController {

    @Resource
    private RoleService roleService;

    @PostMapping("addRole")
    public ResponseEntity<?> addRole(Role role){
        if (roleService.insertRole(role)){
            return new ResponseEntity<>().setState(General.SUCCESS).setMsg("操作成功");
        }else {
            return new ResponseEntity<>().setState(General.ERROR_1001).setMsg("操作失败");
        }
    }

    @PostMapping("roleList")
    public  ResponseEntity<?> roleList(){
       SystemUser systemUser =(SystemUser) getSession().getAttribute(getSession().getId());
        List<Role> list=roleService.selectRoleList(systemUser.getId());
        return new ResponseEntity<>().setData(list).setState(General.SUCCESS).setMsg("数据获取成功");
    }
}
