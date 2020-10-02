package com.web.base;

import com.web.entity.Menu;
import com.web.service.MenuService;
import com.web.util.IdWorker;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseController {
    public   static final   IdWorker idWorker=new IdWorker(1,1);
    public static final Map<String, String> Login_user = new HashMap<>();
    public static final Map<String, String> Login_Out_user = new HashMap<>();

    @Resource
    private MenuService menuService;
    public HttpServletRequest getRequest() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request;
    }
    /**
     * 获取session
     *
     * @return
     */
    public HttpSession getSession() {
        return getRequest().getSession();
    }


    // 获取请求地址 存入菜单Cookie中
    public void saveMenuUrl(String url, HttpServletRequest request) {
        HttpSession session = request.getSession();
        // 获得url
        Menu menu;
        try {
            menu=menuService.getMenuByUrl(url);
            if (menu.getPid() != null) {
                Integer fId = menu.getPid();
                session.setAttribute("fid", fId);
            }
            Integer cid = menu.getId();
            session.setAttribute("cid", cid);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
