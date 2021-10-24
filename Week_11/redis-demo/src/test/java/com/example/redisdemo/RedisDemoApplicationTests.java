package com.example.redisdemo;

import com.example.redisdemo.component.RedisCounter;
import com.example.redisdemo.component.RedisLock;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootTest
class RedisDemoApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private RedisLock lock;

    @Autowired
    private RedisCounter counter;

    @Test
    public void tryLock() {
        System.out.println("try lock => " + lock.tryLock("syw", String.valueOf(1), null));
    }

    @Test
    public void unLock() {
        System.out.println("try unlock => " + lock.unlock("syw", String.valueOf(1)));
    }

    @Test
    public void tryCounter() {

        counter.setCount(5L);

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        int i = 10;

        while (i-- > 0) {
            executorService.execute(this::buy);
        }
        executorService.shutdown();
    }

    private void buy() {
        if (counter.reduce()) {
            System.out.println(Thread.currentThread() + ", 购买->成功");
        } else {
            System.out.println(Thread.currentThread() + ", 购买->失败");
        }
    }

}
