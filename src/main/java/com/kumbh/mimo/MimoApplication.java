package com.kumbh.mimo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class MimoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MimoApplication.class, args);
	}

}
