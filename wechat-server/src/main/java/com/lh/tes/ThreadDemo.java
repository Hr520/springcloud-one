package com.lh.tes;

public class ThreadDemo extends Thread{

   volatile Boolean stop=false;

    @Override
    public void run() {
        while (!stop){
            System.out.println(getName()+" is running");
        }
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("");
            stop=true; //出现异常，修改共享变量
        }
        System.out.println(getName()+" is exiting");
    }

}
