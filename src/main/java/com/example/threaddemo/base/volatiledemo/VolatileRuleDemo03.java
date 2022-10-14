package com.example.threaddemo.base.volatiledemo;

public class VolatileRuleDemo03 {
    public static void main(String[] args){
        final int[] x = {0};
        Thread t1 = new Thread(()->{
            // 此处对共享变量 x 修改
            x[0] = 100;
        });
        // 例如此处对共享变量修改，
        // 则这个修改结果对线程 t1 可见
        // 主线程启动子线程
        t1.start();
        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 子线程所有对共享变量的修改
        // 在主线程调用 t1.join() 之后皆可见
        // 此例中，x==100
        System.out.println(x[0]);
    }
}
