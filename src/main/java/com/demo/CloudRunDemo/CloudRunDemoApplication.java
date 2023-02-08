package com.demo.CloudRunDemo;

import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.apache.catalina.core.ApplicationContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.demo.CloudRunDemo.service.EmployeeService;


@SpringBootApplication
public class CloudRunDemoApplication {
	private static final Logger logger = LogManager.getLogger(CloudRunDemoApplication.class);

	@Autowired
	EmployeeService service;

//	@PostConstruct
	public void batch() {
		logger.info("start the batch");
		try {
			
			service.findAll().forEach(System.out::println);
			TimeUnit.SECONDS.sleep(10);
			logger.info("application cloed");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(CloudRunDemoApplication.class, args);
//		ctx.close();
	}
}
