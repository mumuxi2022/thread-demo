package com.example.threaddemo.base.synchro;

/**
 * @author 帅气的景天
 * @create 2021/7/20 15:26
 */
public class SynDemo {

    //方法同步(作用域是同一个对象）
    public synchronized void test1(){
        try {
            System.out.println(Thread.currentThread().getName()+"开始运行");
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName()+"结束运行");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //方法静态方法
    public static synchronized void test2(){
        try {
            System.out.println(Thread.currentThread().getName()+"开始运行");
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName()+"结束运行");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void test3(){
        System.out.println("sada");
        synchronized(SynDemo.class){
            try {
                System.out.println(Thread.currentThread().getName()+"开始运行");
                Thread.sleep(2000);
                System.out.println(Thread.currentThread().getName()+"结束运行");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }


    public static void main(String[] args) {
        SynDemo demo = new SynDemo();
        SynDemo demo1 = new SynDemo();
//        SynDemo demo2 = new SynDemo();
        new Thread(()->{
            demo.test3();
        },"Thread1").start();
        new Thread(()->{
            demo1.test3();
        },"Thread2").start();
//        new Thread(()->{
//            demo2.test2();
//        },"Thread3").start();
    }
}

