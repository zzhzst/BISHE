package com.zzh.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


/**
 * springboot启动类
 */
@SpringBootApplication
@MapperScan("com.zzh.admin.mapper")
public class psiAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(psiAdminApplication.class, args);
        //查看123456的密文，每次生成不一样但是不影响
        //System.out.println(new BCryptPasswordEncoder().encode("123456"));
    }
}
