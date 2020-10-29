package com.lh.tes;


import java.util.concurrent.ConcurrentHashMap;

public class Test {

    public static void main(String[] args) {
        //Map<Object,Object> myMap=new HashMap<>();
         ConcurrentHashMap myMap=new ConcurrentHashMap();
        for (int i = 0; i <10000 ; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    double a=Math.random();
                    myMap.put(a,a);
                    myMap.remove(a);
                }
            }).start();
        }
        try {
            Thread.sleep(15000l);//多休眠下，保证上面的线程操作完毕。
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Dli dli=Dli.getDli();//全局获取一个对象
        System.out.println(myMap.size());

    }

}
