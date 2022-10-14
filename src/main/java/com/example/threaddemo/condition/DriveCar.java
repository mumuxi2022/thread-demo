package com.example.threaddemo.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 帅气的景天
 * @create 2021/7/16 14:55
 */
public class DriveCar {
    public static void main(String[] args) {
        Lock key = new ReentrantLock();
        Lock car = new ReentrantLock();
        Condition keyCondition = key.newCondition();
        Condition key1Conditon = key.newCondition();
        Thread wife = new Thread(new Wife(key, car,keyCondition), "wife");
        Thread husband = new Thread(new Husband(key, car,keyCondition), "husband");
        wife.start();
        husband.start();
    }
}
