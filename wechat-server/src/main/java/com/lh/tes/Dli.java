package com.lh.tes;

/**
 *
 * 单例模式实例
 * 懒汉式写法 线程安全
 */
public class Dli {

    private static Dli dli;

    private Dli(){}

    public  static synchronized   Dli getDli(){
        if (dli==null){
            dli=new Dli();
        }
        return dli;
    }

}
