package com.example.threaddemo.threadpool;

/**
 * @author 帅气的景天
 * @create 2021/7/27 14:00
 */
public class GotoDemo {
    public static void main(String[] args) {
        for(int i=0;i<3;i++){
            for(int j=3;j<10;j++){
//                if(j==4){
//                    break;
//                }
                if(j==7){
                    continue;
                }
                System.out.println(i+":"+j);
            }
        }
        retry:
            for(int i=0;i<3;i++){
                for(int j=3;j<10;j++){
                    if(j==4){
                        break retry;
                    }
//                    if(j==7){
//                        continue retry;
//                    }
                    System.out.println("加上retry后："+i+":"+j);
                }
            }
    }
}
