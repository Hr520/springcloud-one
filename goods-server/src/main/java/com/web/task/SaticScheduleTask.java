package com.web.task;

import com.web.redis.RedisUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.Resource;


@Configuration
@EnableScheduling
public class SaticScheduleTask {

    private boolean RedisRun = false;
    @Resource
    private RedisUtil redisUtil;

    /*@Scheduled(cron = "0/5 * * * * ?")
    //或直接指定时间间隔，例如：5秒
    //@Scheduled(fixedRate=5000)
    private void configureTasks() {
        System.err.println("执行静态定时任务时间: " + DateUtil.getTime());
    }

    @Scheduled(cron = "0/10 * * * * ?")
    private void InsertUser(){
        ThreadClass threadClass1=new ThreadClass();
        ThreadClass threadClass2=new ThreadClass();
        ThreadClass threadClass3=new ThreadClass();
        ThreadClass threadClass4=new ThreadClass();
        ThreadClass threadClass5=new ThreadClass();
        threadClass1.start();
        threadClass2.start();
        threadClass3.start();
        threadClass4.start();
        threadClass5.start();
        System.err.println("线程启动10秒一次: " + DateUtil.getTime());
    }*/

   /* @Scheduled(cron = "0/10 * * * * ?")
    private void InsertUser() {
        System.out.println("开始redis测试了" + DateUtil.getTime());
        if (RedisRun) {
            System.out.println("已运行过，不再重复执行" + DateUtil.getTime());
            return;
        } else {
            System.out.println("首测redis测试了" + DateUtil.getTime());
            RedisRun = true;
            User user = new User();
            for (int i = 0; i < 100; i++) {
             *//*   user.setId(i);
                user.setPassword("uiuiuiuiuiui"+i);
                user.setName("大哥哥"+i);
                user.setEmail("weqw1232312@qq"+i);
                user.setAge(i);
                redisUtil.hset("USER_INSERT",String.valueOf(i),JSON.toJSONString(user));*//*
                JSONObject jsonObject = JSON.parseObject(redisUtil.hget("USER_INSERT", String.valueOf(i)).toString());
                System.out.println(jsonObject);
            }

        }
        System.out.println("结束redis测试了" + DateUtil.getTime());

    }*/
}
