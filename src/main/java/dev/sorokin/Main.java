package dev.sorokin;

import dev.sorokin.configuration.HibernateConfiguration;
import dev.sorokin.entity.Client;
import dev.sorokin.entity.Profile;
import dev.sorokin.service.ClientService;
import dev.sorokin.service.ProfileService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Main {
	public static void main(String[] args) {

		try (AnnotationConfigApplicationContext context =
					 new AnnotationConfigApplicationContext(HibernateConfiguration.class)) {
				ClientService clientService = context.getBean(ClientService.class);
				ProfileService profileService = context.getBean(ProfileService.class);

			Client client = clientService.getById(44L);
			Profile profile = profileService.profileGet(2L);

//			profileService.profileDelete(2L);

			client.setProfile(profile);
			profile.setClient(client);

			clientService.update(client);
			clientService.printAll();
			}
		}
}
