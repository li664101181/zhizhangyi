package com.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * created By lichang on
 */
@SpringBootApplication
@EnableTransactionManagement //开启事务支持
public class UserApplication { public static void main(String[] args) {
    SpringApplication.run(UserApplication.class,args);
}
}
