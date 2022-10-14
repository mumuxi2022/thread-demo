package com.example.threaddemo.base.deadlock;

/**
 * @author 帅气的景天
 * @create 2021/7/16 14:55
 */
public class DriveCar {
    public static void main(String[] args) {
        String key = "key";
        String car = "car";
        Thread wife = new Thread(new Wife(key, car), "wife");
        Thread husband = new Thread(new Husband(key, car), "husband");
        wife.start();
        husband.start();
    }
}
