package com.example.threaddemo.base.print;

import org.openjdk.jol.info.ClassLayout;

public class ClassLayoutDemo {
    public static void main(String[] args) {
        final ClassLayoutDemo classLayoutDemo = new ClassLayoutDemo();
        Thread t1 = new Thread(() -> {
            synchronized (classLayoutDemo) {
                System.out.println("main抢占锁");
                System.out.println(ClassLayout.parseInstance(classLayoutDemo).toPrintable());
            }
        });
        t1.start();
        synchronized (classLayoutDemo) {
            System.out.println("main抢占锁");
            System.out.println(ClassLayout.parseInstance(classLayoutDemo).toPrintable());
        }


    }
}
