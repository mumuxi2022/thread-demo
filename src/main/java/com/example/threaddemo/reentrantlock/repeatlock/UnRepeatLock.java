package com.example.threaddemo.reentrantlock.repeatlock;

/**
 * @author 帅气的景天老师
 * @create 2021/7/13 18:54
 * 不可重入锁
 */
public class UnRepeatLock {
    private boolean isLocked = false;

    public synchronized void lock() {
        while (isLocked) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        isLocked = true;
    }

    public synchronized void unlock() {
        isLocked = false;
        notify();
    }
}
