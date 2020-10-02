package com.web.Thread;

import com.web.entity.SystemUser;
import com.web.service.SystemUserService;
import com.web.util.SpringUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class ThreadClass  extends Thread{
    @Resource
    private SystemUserService userService;

    @Override
    public void run() {
        userService = (SystemUserService) SpringUtil
                .getBean("userService");
        boolean flag=false;
        SystemUser user;
        for (int i = 0; i <100000 ; i++) {
            user=new SystemUser();
            user.setStatus(i);
            user.setCode("11222"+i);
            user.setNickName("ceshi"+i);
            user.setPasswordLogin("1212121"+i);
         //   flag= userService.addUser(user);
        }
        if (flag){
            this.interrupt();
        }
    }
}
