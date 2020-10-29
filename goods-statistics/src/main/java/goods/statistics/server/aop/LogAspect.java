package goods.statistics.server.aop;

import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect //定义为一个切面类
public class LogAspect {

    @Pointcut("execution(public * goods.statistics.server.service.*.*(..))")
    public void log(){

    }


    @Before("log()")
    public void beforeLog(){
        System.out.println("前置通知");
    }

    @After("log()")
    public void AfterLog(){
        System.out.println("后置通知");
    }

}
