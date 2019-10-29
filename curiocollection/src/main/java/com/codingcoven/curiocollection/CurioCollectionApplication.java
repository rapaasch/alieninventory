package com.codingcoven.curiocollection;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class CurioCollectionApplication {
	public static void main(String[] args) {
		SpringApplication.run(CurioCollectionApplication.class, args);
	}
}
