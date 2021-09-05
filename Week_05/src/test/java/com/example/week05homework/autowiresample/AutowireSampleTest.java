package com.example.week05homework.autowiresample;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * @version 1.0
 * @program: week05homework
 * @description: TODO
 * @author: Philip.Zhang
 * @date: 2021/9/4 11:53 PM
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AutowireConfig.class)
public class AutowireSampleTest {

    @Autowired
    private AutowireSample autowireSample;

    @Test
    public void testAutowireSample(){
        autowireSample.info();
    }
}
