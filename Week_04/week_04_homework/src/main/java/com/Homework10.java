package com;

import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @version 1.0
 * @program: week_04_homework
 * @description: TODO
 * @author: Philip.Zhang
 * @date: 2021/8/29 2:27 PM
 */
public class Homework10 {


    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Lock lock = new ReentrantLock();
        long start=System.currentTimeMillis();
        Homework03 homework03 = new Homework03();
        Condition condition = lock.newCondition();
        // 在这里创建一个线程或线程池，
        // 异步执行 下面方法
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> Homework10.sum());


        // 确保  拿到result 并输出
        System.out.println("异步计算结果为："+future.join());

        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");

//        singleThreadExecutor.shutdown();
    }

    private static int sum() {
        return fibo(36);
    }

    private static int fibo(int a) {
        if ( a < 2){
            return 1;
        }
        return fibo(a-1) + fibo(a-2);
    }
}
