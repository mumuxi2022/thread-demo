package com.example.threaddemo;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @author 帅气的景天老师
 * @create 2022/3/8 17:23
 */
public class Test {
    public static void main(String[] args) throws IOException {
        universalCopy();
        channelBufferCopy();
        channelCopy();

    }
    private static void universalCopy() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("D:\\111.zip");
        FileOutputStream fileOutputStream = new FileOutputStream("D:\\copy\\111_cp1.zip");
        long current = System.currentTimeMillis();
        int len;
        byte[] byteBuffer = new byte[1024 * 1024];
        while ((len = fileInputStream.read(byteBuffer)) != -1){
            fileOutputStream.write(byteBuffer, 0 ,len);
        }
        fileOutputStream.close();
        fileInputStream.close();
        System.out.println("universalCopy take：" + (System.currentTimeMillis() - current));
    }

    private static void channelBufferCopy() throws IOException {

        FileInputStream fileInputStream = new FileInputStream("D:\\111.zip");
        FileOutputStream fileOutputStream = new FileOutputStream("D:\\copy\\111_cp2.zip");
        long current = System.currentTimeMillis();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024 * 1024);
        while (fileInputStream.getChannel().read(byteBuffer) != -1){
            byteBuffer.flip();
            fileOutputStream.getChannel().write(byteBuffer);
            byteBuffer.clear();
        }
        fileOutputStream.close();
        fileInputStream.close();
        System.out.println("channelBufferCopy take：" + (System.currentTimeMillis() - current));
    }

    static void channelCopy() throws IOException{
        FileInputStream fileInputStream = new FileInputStream("D:\\111.zip");
        FileChannel fromChannel = fileInputStream.getChannel();
        FileChannel toChannel = FileChannel.open(Paths.get("D:\\copy\\111_cp3.zip"), StandardOpenOption.CREATE, StandardOpenOption.WRITE);
        long current = System.currentTimeMillis();
        fromChannel.transferTo(0, fileInputStream.available(), toChannel);
        toChannel.close();
        fromChannel.close();
        System.out.println("channelCopy take：" + (System.currentTimeMillis() - current));
    }
}
