package com.example.week05homework.autowiresample;

import org.springframework.stereotype.Component;

/**
 * @version 1.0
 * @program: week05homework
 * @description: TODO
 * @author: Philip.Zhang
 * @date: 2021/9/4 11:37 PM
 */
@Component
public class AutowireSample {
    public AutowireSample(){
        System.out.println("autowired");
    }
    public void info(){
        System.out.println("AutowireSample");
    }
}
