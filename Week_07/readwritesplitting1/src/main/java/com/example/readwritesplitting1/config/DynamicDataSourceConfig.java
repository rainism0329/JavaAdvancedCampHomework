package com.example.readwritesplitting1.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.example.readwritesplitting1.datasource.DynamicDataSource;
import com.example.readwritesplitting1.utils.DatasourceConst;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @version 1.0
 * @program: readwritesplitting1
 * @description: TODO
 * @author: Philip.Zhang
 * @date: 2021/9/21 10:47 PM
 */
@Configuration
public class DynamicDataSourceConfig {

    @Bean
    @ConfigurationProperties("spring.datasource.druid.master")
    public DataSource masterDataSource() {
        return DruidDataSourceBuilder.create().build();
    }


    @Bean
    @ConfigurationProperties("spring.datasource.druid.slave")
    public DataSource slaveDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    @Primary
    public DynamicDataSource dataSource(DataSource masterDataSource, DataSource slaveDataSource) {
        Map<Object, Object> targetDataSources = new HashMap<>(5);
        targetDataSources.put(DatasourceConst.MASTER, masterDataSource);
        targetDataSources.put(DatasourceConst.SLAVE, slaveDataSource);
        return new DynamicDataSource(masterDataSource, targetDataSources);
    }
}
