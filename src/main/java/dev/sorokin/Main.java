package dev.sorokin;

import dev.sorokin.configurations.HibernateConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class Main {

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
		try(AnnotationConfigApplicationContext context =
					new AnnotationConfigApplicationContext(HibernateConfiguration.class)){

		}
	}
}
