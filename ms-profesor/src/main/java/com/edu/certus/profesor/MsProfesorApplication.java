package com.edu.certus.profesor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@EnableFeignClients("com.edu.certus.profesor.client")
@SpringBootApplication
public class MsProfesorApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsProfesorApplication.class, args);
	}

}
