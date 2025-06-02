package dev.sorokin;

import dev.sorokin.configurations.HibernateConfiguration;
import dev.sorokin.entity.Client;
import dev.sorokin.service.ClientService;
import dev.sorokin.service.TransactionHelper;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.format.DateTimeFormatter;
import java.util.List;

//@SpringBootApplication
public class Main {
	public static void main(String[] args) {

		Client client = new Client("James", "segg2@gagaga.gigi");
		Client client1 = new Client("Maximka", "Chlenososik1@gigach.adi");

		try (AnnotationConfigApplicationContext context =
					 new AnnotationConfigApplicationContext(HibernateConfiguration.class)) {
				ClientService clientService = context.getBean(ClientService.class);
				clientService.printAll();

				clientService.update(15L, "Endrew", "JakartaTebe@Sosi.hui");

				clientService.create(client1);
				clientService.printAll();

			}
///добавить клиента
//			clientService.create(client);

///удалить клиента
//		clientService.delete(2L);
		}
}
