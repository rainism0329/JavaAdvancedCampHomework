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
 * @date: 2021/8/29 2:15 PM
 */
public class Homework08 {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Lock lock = new ReentrantLock();
        long start=System.currentTimeMillis();
        Homework03 homework03 = new Homework03();
        Condition condition = lock.newCondition();
        // 在这里创建一个线程或线程池，
        // 异步执行 下面方法
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        Future<Integer> future = singleThreadExecutor.submit(new Callable<Integer>() {
            @Override
            public Integer call() {
                synchronized (this) {
                    return Homework08.sum();
                }
            }
        });

        Integer result = future.get();

        // 确保  拿到result 并输出
        System.out.println("异步计算结果为："+result);

        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");

        singleThreadExecutor.shutdown();
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
