package com.example.threaddemo.threadpool.my;

/**
 * @author 帅气的景天老师
 * @create 2023/2/23 15:01
 */
public interface GpRejectExecutionHandler {

    void rejectedExecution(Runnable r, GpThreadPool executor);
}
