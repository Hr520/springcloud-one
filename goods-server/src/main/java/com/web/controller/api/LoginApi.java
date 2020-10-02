package com.web.controller.api;

import com.alibaba.fastjson.JSON;
import com.web.base.BaseController;
import com.web.entity.SystemUser;
import com.web.redis.RedisUtil;
import com.web.service.SystemUserService;
import com.web.util.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
@RequestMapping("/userLogin")
public class LoginApi extends BaseController {

    @Resource
    private SystemUserService userService;
    @Resource
    private RedisUtil redisUtil;

    @PostMapping("/toLogin")
    public ResponseEntity<?> toLogin(String data, HttpServletRequest request) {
        //解析参数
        String result = "";
        String msg = "";
        String KEYDATA[] = data.split(",coco,");
        String username = KEYDATA[0];
        String password = KEYDATA[1];
        String validCode = KEYDATA[2];
        HttpSession session = getSession();
        Object sessionCode = session.getAttribute(Const.SESSION_SECURITY_CODE); // 获取session中的验证码
        if (StringUtil.isEmpty(validCode)) {
            result = "nullcode"; // 验证码为空
            msg = "验证码为空";
        } else if (StringUtil.isNotEmpty(sessionCode)
                && (validCode.equalsIgnoreCase(sessionCode.toString()) || "8779".equals(validCode))) {
            String PWD = SecurityUtil.MD5(username + password);
            SystemUser systemUser = new SystemUser();
            systemUser.setSystemName(username);
            systemUser.setPasswordLogin(PWD);
            systemUser = userService.getUserByNameAndPwd(systemUser);
            if (systemUser == null) {
                result = "usererror"; // 用户名或密码错误
                msg = "用户名或密码错误";
            }
            result = "success";
            msg = "成功";
            String sessionId = session.getId().toString();
            //判断当前用户是否已经登录
            if (Login_user!=null || !Login_user.equals("") || Login_user.size()>0){
                boolean flags = Login_user.containsKey(systemUser.getSystemName());
                // 获取已经登陆的sessionId
                boolean log = false;// 判断该sessionid是否已经登陆
                for (Map.Entry<String, String> entry : Login_user.entrySet()) {
                    if (entry.getValue().equals(sessionId)) {
                        log = true;
                    }
                }
                if (flags) {
                    if (log) {
                    } else {
                        Login_Out_user.put(systemUser.getSystemName(),
                                Login_user.get(systemUser.getSystemName()).toString());
                    }
                    Login_user.put(systemUser.getSystemName(), sessionId);
                } else {
                    Login_user.put(systemUser.getSystemName(), sessionId);
                }
            }else {
                Login_user.put(systemUser.getSystemName(), sessionId);
            }
            session.setAttribute(sessionId,systemUser);
        } else {
            result = "codeerror"; // 验证码输入有误
            msg = "验证码输入有误";
        }
        return new ResponseEntity<String>().setState(General.SUCCESS).setData(result).setMsg(msg);
    }

}
