package com.web.controller.api;

import com.web.base.BaseController;
import com.web.entity.RoleMenu;
import com.web.entity.SystemUser;
import com.web.entity.UserRole;
import com.web.service.UserRoleMenuService;
import com.web.service.UserRoleService;
import com.web.util.General;
import com.web.util.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("userRoleMenuApi")
public class UserRoleMenuApi extends BaseController {
    @Resource
    private UserRoleMenuService userRoleMenuService;
    @Resource
    private UserRoleService userRoleService;

    @PostMapping("userRoleMenuList")
    public ResponseEntity<?> userRoleMenuList(){
        //获取用户的全部角色;
        HttpSession session=getSession();
        String SessionId=session.getId();
        SystemUser systemUser=(SystemUser)session.getAttribute(SessionId);
        List<UserRole> list=userRoleService.getUserRoleListByUserId(systemUser.getId());
        List<Integer> roleIdList=list.stream().map(UserRole::getRoleId).distinct().collect(Collectors.toList());
        List<RoleMenu> roleMenus=userRoleMenuService.getUserRoleMenuListInRoleId(roleIdList);
        return new ResponseEntity<>().setState(General.SUCCESS).setData(roleMenus).setMsg("数据获取成功");
    }
}
