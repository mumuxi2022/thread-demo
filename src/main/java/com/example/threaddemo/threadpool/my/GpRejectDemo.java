package com.example.threaddemo.threadpool.my;

/**
 * @author 帅气的景天老师
 * @create 2023/2/23 15:07
 */
public class GpRejectDemo implements GpRejectExecutionHandler{
    @Override
    public void rejectedExecution(Runnable r, GpThreadPool executor) {
        System.out.println("拒绝");
    }
}
