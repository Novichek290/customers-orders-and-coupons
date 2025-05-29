package dev.sorokin.configurations;

import dev.sorokin.entity.Client;
import dev.sorokin.entity.Profile;
import org.hibernate.SessionFactory;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EntityScan(basePackages = "dev.sorokin.entity")
public class HibernateConfiguration {
    @Bean
    private static SessionFactory sessionFactory() {
        return new org.hibernate.cfg.Configuration()
                .configure()
                .addAnnotatedClass(Client.class)
                .addAnnotatedClass(Profile.class)
                .buildSessionFactory();
    }
}
