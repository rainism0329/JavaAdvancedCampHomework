package com.example.week05homework.xmlsample;

import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @version 1.0
 * @program: week05homework
 * @description: TODO
 * @author: Philip.Zhang
 * @date: 2021/9/5 12:40 AM
 */

public class XMLSampleTest {

    @Test
    public void testXMLSample() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("XMLSample.xml");
        XMLSample xmlSample = ((XMLSample) applicationContext.getBean("XMLSample"));

        xmlSample.info();
    }
}
