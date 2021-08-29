package com;

/**
 * @version 1.0
 * @program: week_04_homework
 * @description: TODO
 * @author: Philip.Zhang
 * @date: 2021/8/29 11:59 AM
 */
public class Homework03 {
    //这是得到的返回值
    private static int result = 0;

    public static void main(String[] args) throws InterruptedException {

        long start=System.currentTimeMillis();
        Homework03 homework03 = new Homework03();
        // 在这里创建一个线程或线程池，
        // 异步执行 下面方法
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (homework03){
                    result =Homework03.sum();
                    homework03.notifyAll();
                }
            }
        });
        thread.start();
        synchronized (homework03){
            homework03.wait();
        }
        // 确保  拿到result 并输出
        System.out.println("异步计算结果为："+result);

        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");

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
