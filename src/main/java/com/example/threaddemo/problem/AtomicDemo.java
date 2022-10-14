package com.example.threaddemo.problem;

/**
 * @author 帅气的景天
 * @create 2021/7/20 14:54
 */
public class AtomicDemo {
    public static int count=0; //100
    public static void add(){
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        count++;
    }

    public static void main(String[] args) {
        for(int i=0;i<1000;i++){
            new Thread(()-> AtomicDemo.add()).start();
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("运行结果："+count);
    }
}
