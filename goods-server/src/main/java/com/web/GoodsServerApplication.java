package com.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
@EnableDiscoveryClient
@SpringBootApplication
/**
 * 添加扫描mapper注解
 *
 */
@MapperScan("com.web.mapper")
@ServletComponentScan
public class GoodsServerApplication {
	public static void main(String[] args) {
		SpringApplication.run(GoodsServerApplication.class, args);
	}

}
