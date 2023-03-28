package com.example.threaddemo.threadlocal.scene1;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author 帅气的景天老师
 * @create 2022/1/19 14:14
 */
public class ThreadLocalDemo03 {
    public static ExecutorService threadPool = Executors.newFixedThreadPool(16);
    public static SimpleDateFormat girl = new SimpleDateFormat("mm:ss");
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 1000; i++) {
            int s = i;
            threadPool.submit(()->{
                Date date = new Date(1000*s);
                String data = girl.format(date);
                System.out.println(data);
            });
        }
        threadPool.shutdown();
    }
}
