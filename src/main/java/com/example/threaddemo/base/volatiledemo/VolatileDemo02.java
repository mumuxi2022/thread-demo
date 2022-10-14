package com.example.threaddemo.base.volatiledemo;

public class VolatileDemo02 {
    //    public static boolean stop = false;
    public volatile static boolean stop = false;//3、加个volatile也可以结束

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            int i = 0;
            while (!stop) {
                i++;
//               try {
//                   Thread.sleep(5);
//               } catch (InterruptedException e) {
//                   e.printStackTrace();
//               }//2、睡眠一下也可以结束
//               System.out.println("rs:"+i);//1、打印放在循环里面也可以结束
            }
            System.out.println("rs:" + i);
        });
        thread.start();
        Thread.sleep(1000);
        stop = true;
    }
}
