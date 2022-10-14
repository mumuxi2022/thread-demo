package com.example.threaddemo.base.volatiledemo;

public class VolatileDemo01 {
    public static boolean stop = false;

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            int i = 0;
            while (!stop) {
                i++;
            }
            System.out.println("rs:" + i);
        });

        thread.start();
        Thread.sleep(1000);
        int a=0;
        stop = true;
    }
}
