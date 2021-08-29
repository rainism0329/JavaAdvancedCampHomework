package com;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @version 1.0
 * @program: week_04_homework
 * @description: TODO
 * @author: Philip.Zhang
 * @date: 2021/8/29 12:20 PM
 */
public class Homework06 {
    //这是得到的返回值
    private static volatile int result = 0;

    public static void main(String[] args) throws InterruptedException {

        Lock lock = new ReentrantLock();
        long start=System.currentTimeMillis();
        Homework03 homework03 = new Homework03();
        Condition condition = lock.newCondition();
        // 在这里创建一个线程或线程池，
        // 异步执行 下面方法
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(1);
        fixedThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                synchronized (homework03){
                    result =Homework06.sum();
                    homework03.notifyAll();
                    lock.lock();
                    try {

                        condition.signalAll();
                    }finally {

                        lock.unlock();
                    }
                }
            }});
        lock.lock();
        try {

            condition.await();
        }finally {

            lock.unlock();
        }

        // 确保  拿到result 并输出
        System.out.println("异步计算结果为："+result);

        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
        fixedThreadPool.shutdown();

        // 然后退出main线程
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
