package com.example.readwritesplitting1.datasource;

import com.example.readwritesplitting1.utils.DatasourceConst;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @version 1.0
 * @program: readwritesplitting1
 * @description: TODO
 * @author: Philip.Zhang
 * @date: 2021/9/21 11:43 PM
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CurDataSource {

    String value() default DatasourceConst.MASTER;

}
