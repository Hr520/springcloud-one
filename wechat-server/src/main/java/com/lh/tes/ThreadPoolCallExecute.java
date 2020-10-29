package com.lh.tes;

import com.sun.corba.se.impl.resolver.SplitLocalResolverImpl;

import java.util.Random;
import java.util.concurrent.*;

public class ThreadPoolCallExecute {

    private  static ExecutorService executorService= Executors.newFixedThreadPool(5);
    private static CountDownLatch counter=new CountDownLatch(1);

    public static void main(String[] args) {
        try {
            Thread a=new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("逻辑A");
                    counter.countDown();
                }

            });
            a.start();
            for (int i = 0; i <5 ; i++) {
                Future<Integer> future=executorService.submit(new Caller(12));
            }
        } catch (Exception e) {
            e.printStackTrace();

        }finally {
            executorService.shutdown(); //必须要关闭
        }
    }

    static class Caller implements Callable{
    private int param;
    public  Caller (int param){
        this.param=param;
    }

        @Override
        public Object call() throws Exception {
        counter.await();
            System.out.println("逻辑B");
            param=param+new Random().nextInt();
            return param;
        }
    }
}
