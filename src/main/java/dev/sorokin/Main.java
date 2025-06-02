package dev.sorokin;

import dev.sorokin.configurations.HibernateConfiguration;
import dev.sorokin.entity.Client;
import dev.sorokin.service.ClientService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
	public static void main(String[] args) {

		Client client = new Client("James", "segg2@gagaga.gigi");
		Client client1 = new Client("Max", "Chlen@gigach.adi");

		try (AnnotationConfigApplicationContext context =
					 new AnnotationConfigApplicationContext(HibernateConfiguration.class)) {
				ClientService clientService = context.getBean(ClientService.class);
				clientService.printAll();

				clientService.update(15L, "Endrew", "JakartaTebe@Sosi.hui");

				clientService.create(client1);
				clientService.printAll();

			}
		}
}
