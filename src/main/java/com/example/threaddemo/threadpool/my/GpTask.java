package com.example.threaddemo.threadpool.my;

/**
 * @author 帅气的景天老师
 * @create 2023/2/23 15:09
 */
class GpTask implements Runnable {
    @Override
    public void run() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName());
    }
}
