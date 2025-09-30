package dev.sorokin.service;

import dev.sorokin.entity.Client;
import dev.sorokin.entity.ClientOrder;
import dev.sorokin.entity.Profile;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class InputManager {
    private final Scanner scanner = new Scanner(System.in);
    public Client addClient() {
        Client client = new Client();
            String inputData = scanner.nextLine();
            String[] data = inputData.split(" ");
            client.setName(data[0].trim());
            client.setEmail(data[1].trim());
        return client;
    }

    public Profile addProfile(){
        Profile profile = new Profile();
        return profile;
    }

    public Profile addProfileTo(Long id) {

        return null;
    }

    public ClientOrder addOrderTo(){
        ClientOrder clientOrder = new ClientOrder();

        return clientOrder;
    }
}
