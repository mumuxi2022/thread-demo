package com.example.threaddemo.model;

public class Bread implements Runnable {
    @Override
    public void run() {
        System.out.println("一片面包");
    }
}
