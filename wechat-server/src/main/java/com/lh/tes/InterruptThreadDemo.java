package com.lh.tes;

public class InterruptThreadDemo {
    public static void main(String[] args) throws InterruptedException {
        ThreadDemo threadDemo=new ThreadDemo();
        System.out.println("start thread ...");
        threadDemo.start();
        threadDemo.wait(1000);
        //Thread.sleep(3000);
        System.out.println("interrupt thread ....:"+ threadDemo.getName());
        threadDemo.stop=true;
        threadDemo.interrupt();
        //Thread.sleep(3000);
        System.out.println("stop application");
    }
}
