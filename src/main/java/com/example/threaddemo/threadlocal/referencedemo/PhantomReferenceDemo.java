package com.example.threaddemo.threadlocal.referencedemo;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

/**
 * @author 帅气的景天老师
 * @create 2022/6/7 16:26
 */
public class PhantomReferenceDemo {
    public static void main(String[] args) throws InterruptedException {
        Object b = new Object();
        PhantomReference sr = new PhantomReference(b,new ReferenceQueue());
        b = null;
        System.gc();
        Thread.sleep(1000);
        System.out.println(sr.get());
    }
}
