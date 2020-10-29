package com.lh.tes;

public class ThreadRunnable implements Runnable {
    @Override
    public void run() {
        /**
         *
         * 处理业务
         *
         */
        System.out.println("线程运行");
    }

    public static void main(String[] args) {
        ThreadRunnable threadRunnable=new ThreadRunnable();
        Thread thread=new Thread(threadRunnable);
        thread.start();
    }
}
