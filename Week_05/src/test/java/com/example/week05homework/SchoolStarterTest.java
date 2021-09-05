package com.example.week05homework;

import com.phil.starter.config.SchoolAutoConfig;
import com.phil.starter.service.School;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * @version 1.0
 * @program: week05homework
 * @description: TODO
 * @author: Philip.Zhang
 * @date: 2021/9/5 5:12 AM
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = SchoolAutoConfig.class)
public class SchoolStarterTest {
    @Autowired
    School school;

    @Test
    public void testSchool(){
        System.out.println(school.toString());
    }
}
