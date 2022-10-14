package com.example.threaddemo.base.interrupt;

/**
 * @author 帅气的景天老师
 * @create 2022/5/20 14:37
 */
public class FlagDemo {
    static volatile boolean flag = false;
    public static void main(String[] args) throws InterruptedException {
        new Thread(()->{
            while(!flag){
                System.out.println(Thread.currentThread().getName()+"在执行");
            }
        }).start();
        Thread.sleep(1000);
        flag = true;
    }
}
