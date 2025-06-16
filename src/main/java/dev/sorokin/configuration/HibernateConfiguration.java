package dev.sorokin.configuration;

import dev.sorokin.entity.Client;
import dev.sorokin.entity.Order;
import dev.sorokin.entity.Profile;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "dev.sorokin")
public class HibernateConfiguration {
    @Bean
    public static SessionFactory sessionFactory() {
        return new org.hibernate.cfg.Configuration()
                .configure("hibernate.cfg.xml") // Явно укажите конфиг
                .setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect")
                .addAnnotatedClass(Client.class)
                .addAnnotatedClass(Profile.class)
                .addAnnotatedClass(Order.class)
                .buildSessionFactory();
    }
}
