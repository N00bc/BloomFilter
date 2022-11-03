package com.cyn.controller;

import com.cyn.service.UsrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author G0dc
 * @description:
 * @date 2022/11/1 16:17
 */
@RestController("/hello")
public class HelloController {
    @GetMapping("/meet")
    public String hello() {
        String hello = "hello world";
        System.out.println("hello = " + hello);
        return hello;
    }
    @Autowired
    private UsrService usrService;

    @GetMapping("/user/{id}")
    public String queryUser(@PathVariable("id")Integer id){
        return usrService.queryUser(id);
    }
}
