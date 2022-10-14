package com.example.threaddemo.base.joindemo;

/**
 * @author 帅气的景天老师
 * @create 2021/7/9 22:06
 */
public class JoinDemo {
    private static int i = 10;

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            i = 30;
        });
        t.start();
        //t线程中的执行结果对main线程可见
        t.join();//Happens-Before模型
        System.out.println("i=" + i);
    }
}
