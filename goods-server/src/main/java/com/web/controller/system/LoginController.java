package com.web.controller.system;


import com.alibaba.fastjson.JSON;
import com.web.base.BaseController;
import com.web.entity.*;
import com.web.redis.RedisUtil;
import com.web.service.*;
import com.web.util.Const;
import com.web.util.DateUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import vo.MenuVo;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/*
 * 总入口
 */
@Controller
public class LoginController extends BaseController {

    @Resource
    private SystemUserService userService;
    @Resource
    private MenuService menuService;
    @Resource
    private UserRoleService userRoleService;
    @Resource
    private UserRoleMenuService userRoleMenuService;
    @Resource
    private RedisUtil redisUtil;
    @Resource
    private RoleService roleService;


    /**
     * 访问登录页
     *
     * @return
     */
    @GetMapping(value = "/login")
    public String toLogin() {
        //判断当前用户是否已经登录 获取sessionID
        // 获取当前的session id 是否与系统中登录的用户sessionid一样
        String session_id = getSession().getId().toString();
        if (Login_user.values().contains(session_id)) {
            return "/index";
        } else {
            return "/view/login";
        }

    }

    @GetMapping(value = "/home")
    public String index(Model model) {
        //根据sessionId 获取当前用户的ID;
        HttpSession session = getSession();
        SystemUser systemUser = (SystemUser) session.getAttribute(session.getId());
        Integer UserId = systemUser.getId();
        //判断当前用户的菜单数据是否已经缓存
        Object object = redisUtil.get(Const.CACHE_REDIS_USER_MENU_ + UserId);
        List<MenuVo> menuVos = new ArrayList<>();
        //获取用户角色
        Role role=new Role();
        List<Role> roles=roleService.roleList(UserId);
        if (roles!=null && roles.size()>0){
            roles.sort(Comparator.comparing(Role :: getId));
            role= roles.get(0);
        }

        if (object == null || object.equals("")) {
            List<UserRole> userRoles = userRoleService.getUserRoleListByUserId(UserId);
            if (userRoles == null || userRoles.size() == 0) {
                return "error";
            }
            List<Integer> roleIdList = userRoles.stream().map(UserRole::getRoleId).distinct().collect(Collectors.toList());
            if (roleIdList.size() == 0 || roleIdList == null) {
                return "error";
            }
            List<RoleMenu> roleMenus = userRoleMenuService.getUserRoleMenuListInRoleId(roleIdList);
            if (roleMenus.size() == 0 || roleMenus == null) {
                return "error";
            }
            List<Menu> menuList = menuService.getMenuInId(roleMenus.stream().map(RoleMenu::getMenuId).collect(Collectors.toList()));
            if (menuList ==null || menuList.size()==0){return  "error";}
            List<Menu> fList = menuList.stream().filter(e -> e.getPid() == 0).collect(Collectors.toList());
            List<MenuVo> f_json_list = JSON.parseArray(JSON.toJSONString(fList), MenuVo.class);
            Map<Integer, List<Menu>> map = menuList.stream().collect(Collectors.groupingBy(Menu::getPid));
            for (Map.Entry<Integer, List<Menu>> entry : map.entrySet()) {
                for (int i = 0; i < f_json_list.size(); i++) {
                    if (entry.getKey().equals(fList.get(i).getId())) {
                        f_json_list.get(i).setSubMenu(entry.getValue());
                    }
                }
            }
            menuVos = f_json_list;
            redisUtil.set(Const.CACHE_REDIS_USER_MENU_ + UserId, JSON.toJSONString(menuVos));
        } else {
            menuVos = JSON.parseArray(object.toString(), MenuVo.class);
        }
        model.addAttribute("menuVos",menuVos);
        model.addAttribute("role",role);
        model.addAttribute("today", DateUtil.getDay());
        model.addAttribute("msgcount",10);
        session.setAttribute("menuVos",menuVos);
        session.setAttribute("role",role);
        session.setAttribute("today",DateUtil.getDay());
        return "/index";
    }

    @GetMapping(value = "/erro")
    public String erro() {
        return "/view/erro";
    }
    @GetMapping("/logout")
    public String logout(){
        HttpSession  session=getSession();
        session.removeAttribute("menuVos");
        session.removeAttribute("role");
        session.removeAttribute("today");
        SystemUser systemUser=(SystemUser) session.getAttribute(session.getId());
        Login_user.remove(systemUser.getSystemName());
        session.removeAttribute(session.getId());
        session.removeAttribute("menuVos");
        session.removeAttribute("menuVos");
        session.removeAttribute("menuVos");
        return "/view/login";
    }

}
