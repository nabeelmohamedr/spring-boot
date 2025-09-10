package com.optimus.omnitrix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebAutoConfiguration;

@SpringBootApplication// (exclude = {SpringDataWebAutoConfiguration.class})
public class OmnitrixApplication {

	public static void main(String[] args) {

        SpringApplication.run(OmnitrixApplication.class, args);
	}

}
