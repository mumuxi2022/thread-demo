package com.example.threaddemo.base.interrupt;

import java.util.concurrent.TimeUnit;

public class InterruptDemo01 implements Runnable {
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {//获取中断标记，默认是false
            try {
                TimeUnit.SECONDS.sleep(10);
                System.out.println("正常执行");
            } catch (InterruptedException e) {
                //有一个默认操作，复位
                //复位 false
//                Thread.currentThread().interrupt();//true
                e.printStackTrace();
                System.out.println(Thread.interrupted()); //再次复位
            }
            System.out.println("线程循环");
        }
        System.out.println("结束");
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new InterruptDemo01());
        thread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread.interrupt();
    }
}
