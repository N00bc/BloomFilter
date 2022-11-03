package com.cyn;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author G0dc
 * @description:
 * @date 2022/11/1 16:15
 */
@SpringBootApplication
@MapperScan(value = "com.cyn.mapper")
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
