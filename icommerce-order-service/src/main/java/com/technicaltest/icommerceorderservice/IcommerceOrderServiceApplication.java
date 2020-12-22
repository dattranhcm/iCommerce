package com.technicaltest.icommerceorderservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@SpringBootApplication
@EnableAutoConfiguration
@EnableRedisHttpSession
public class IcommerceOrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(IcommerceOrderServiceApplication.class, args);
	}

}
