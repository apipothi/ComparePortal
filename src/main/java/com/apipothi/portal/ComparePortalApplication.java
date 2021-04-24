package com.apipothi.portal;
/*
 * Author    : API POTHI
 * YouTube   : https://www.youtube.com/apipothi
 * Web Site  : http://apipothi.com/
 * Play List : MICROSERVICE-SPRINGBOOT
 * JAVA      : 1.8
*/
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
//http://localhost:8083/swagger-ui.html
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import brave.sampler.Sampler;

//http://localhost:8083/swagger-ui.html

@SpringBootApplication
@EnableFeignClients("com.apipothi.portal")
@EnableDiscoveryClient
public class ComparePortalApplication {
	private final static Logger logger = LoggerFactory.getLogger(ComparePortalApplication.class);
	public static void main(String[] args) {
		logger.info("******-ComparePortalApplication-START");
		SpringApplication.run(ComparePortalApplication.class, args);
		logger.info("******-ComparePortalApplication-START");
	}

	@Bean
	public Sampler defaultSampler() {
		return Sampler.ALWAYS_SAMPLE;
	}
}
