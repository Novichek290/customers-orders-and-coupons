package dev.sorokin;

import dev.sorokin.configuration.HibernateConfiguration;
import dev.sorokin.design.Color;
import dev.sorokin.entity.Client;
import dev.sorokin.entity.Profile;
import dev.sorokin.service.ClientService;
import dev.sorokin.service.ProfileService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDateTime;


public class Main {
    public static void main(String[] args) {

        try (AnnotationConfigApplicationContext context =
                     new AnnotationConfigApplicationContext(HibernateConfiguration.class)) {
            ClientService clientService = context.getBean(ClientService.class);
            ProfileService profileService = context.getBean(ProfileService.class);

            Client client = Client.builder()
                    .name("Adam")
                    .email("IFuckEvaToo@in.ass")
                    .dateTime(LocalDateTime.now())
                    .build();

            Profile profile = Profile.builder()
                    .address("Gods heaven, 1")
                    .phone("not-your-business")
                    .build();

            profile.setClient(client);
            client.setProfile(profile);

//            clientService.printAll();
            System.out.print(Color.getBLUE() + "-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-");
            System.out.println("-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-" + Color.getRESET());
            clientService.create(client);
            System.out.print(Color.getBLUE() + "-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-");
            System.out.println("-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-" + Color.getRESET());

//            for(Client mrX : clientService.getAll()) {
//                clientService.delete(mrX.getId());
//            }
            clientService.printAll();
        }
    }
}
