package com.optimus.omnitrix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.github.cdimascio.dotenv.Dotenv;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebAutoConfiguration;

@SpringBootApplication// (exclude = {SpringDataWebAutoConfiguration.class})
public class OmnitrixApplication {

	public static void main(String[] args) {

        Dotenv dotenv = Dotenv.configure()
                .directory(System.getProperty("user.dir"))
                .ignoreIfMalformed()
                .ignoreIfMissing()
                .load();

        // Set DB Url  -Dspring.profiles.active=production
        if (dotenv.get("DB_URL") != null) {
            System.setProperty("DB_URL", dotenv.get("DB_URL"));
        }
        // Set DB User
        if (dotenv.get("DB_USER") != null) {
            System.setProperty("DB_USER", dotenv.get("DB_USER"));
        }

        // Set DB Password
        if (dotenv.get("DB_PASSWORD") != null) {
            System.setProperty("DB_PASSWORD", dotenv.get("DB_PASSWORD"));
        }


        SpringApplication.run(OmnitrixApplication.class, args);
	}

}
