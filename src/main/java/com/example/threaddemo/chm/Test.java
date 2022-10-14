package com.example.threaddemo.chm;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

public class Test {
    public static void main(String[] args) {
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap(5);
        concurrentHashMap.put("key", "value");
        concurrentHashMap.get("key");
        concurrentHashMap.size();

        HashMap map = new HashMap(5);
//        HashMap map1 = new HashMap(5,0.55f);
        map.put("key", "123");
        map.get("key");

        ReentrantLock reentrantLock = new ReentrantLock();
        Task task = new  Task(reentrantLock);
        new Thread(task,"Thread1").start();
        new Thread(task,"Thread2").start();

    }
}

class Task implements Runnable{
    ReentrantLock lock;

    public Task(ReentrantLock lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            lock.lock();
            while (true){
                System.out.println(Thread.currentThread().getName());
            }
        }finally {
            lock.unlock();
        }
    }
}
