package com.example.week05homework.javacodesample;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @version 1.0
 * @program: week05homework
 * @description: TODO
 * @author: Philip.Zhang
 * @date: 2021/9/5 1:24 AM
 */
@Configuration
public class JavaCodeConfig {
    @Bean(name = "javaCodeSample")
    public JavaCodeSample getJavaCodeSample(){
        return new JavaCodeSample();
    }
}
