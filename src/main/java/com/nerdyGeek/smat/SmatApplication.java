package com.nerdyGeek.smat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@ConfigurationPropertiesScan
@EnableFeignClients
public class SmatApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmatApplication.class, args);
	}

}
