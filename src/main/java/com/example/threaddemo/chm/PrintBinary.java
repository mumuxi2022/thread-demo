package com.example.threaddemo.chm;

/**
 * @author 帅气的景天老师
 * @create 2022/10/26 14:25
 */
public class PrintBinary {
    public static void main(String[] args) {
        int a = Integer.numberOfLeadingZeros(16) | (1 << (16 - 1));
        printBinary(a);

    }
    /*
    打印一个数的二进制码
    */
    public static void printBinary(int a){
        System.out.println(a);
        for (int i = 31; i >= 0; i--){
            System.out.print(((a >> i) & 1));
        }
    }
}
