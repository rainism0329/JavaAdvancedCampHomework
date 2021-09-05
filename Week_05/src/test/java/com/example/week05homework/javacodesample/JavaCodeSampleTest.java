package com.example.week05homework.javacodesample;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

/**
 * @version 1.0
 * @program: week05homework
 * @description: TODO
 * @author: Philip.Zhang
 * @date: 2021/9/5 1:26 AM
 */
public class JavaCodeSampleTest {
    @Test
    public void testJavaCodeSample(){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(JavaCodeConfig.class);
        JavaCodeSample javaCodeSample = (JavaCodeSample) applicationContext.getBean("javaCodeSample");

        javaCodeSample.info();
    }
}
