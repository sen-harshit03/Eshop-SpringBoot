package com.harshit.eshop;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;


@SpringBootApplication
@EnableCaching
public class EshopApplication {

	private static final Logger log = LoggerFactory.getLogger(EshopApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(EshopApplication.class, args);
		log.info("Lesssgoooo");
	}

}
