package com.example.threaddemo.threadlocal.scene1;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author 帅气的景天老师
 * @create 2022/1/19 14:14
 */
public class ThreadLocalDemo04 {
    public static ExecutorService threadPool = Executors.newFixedThreadPool(16);
    public static ThreadLocal<SimpleDateFormat> girl = ThreadLocal.withInitial(() -> new SimpleDateFormat("mm:ss"));
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 1000; i++) {
            int s = i;
            threadPool.submit(()->{
                Date date = new Date(1000*s);
                SimpleDateFormat dateFormat = girl.get();
                String data = dateFormat.format(date);
                System.out.println(data);
                //此方法返回给定对象的哈希码值，
                //这个哈希码值实际上是对象在内存中的地址的映射，因此可以用来表示对象的内存地址。
                //这个哈希码值并不是对象的唯一标识符，因为它是由对象的内存地址计算得出的，
                //因此它可能会因为JVM的不同实现或不同运行时环境而发生变化。
//                System.out.println(System.identityHashCode(dateFormat));
            });
        }
        threadPool.shutdown();
    }
}
