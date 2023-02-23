package com.example.threaddemo.threadpool.my;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author 帅气的景天老师
 * @create 2023/2/23 15:04
 */
public class Test {
    public static void main(String[] args) {
        GpThreadPool gpThreadPool = new GpThreadPool(2,
                4,
                2,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(50),
                Executors.defaultThreadFactory(),
                new GpRejectDemo());

        for (int i = 0; i < 10; i++) {
            gpThreadPool.execute(new GpTask());

        }
    }
}
