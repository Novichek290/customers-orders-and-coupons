package dev.sorokin.service.command;

import dev.sorokin.contract.Command;
import dev.sorokin.service.InputManager;
import dev.sorokin.service.contoller.ClientController;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientCreateCommand implements Command {
    private ClientController clientController;

    @Transactional
    @Override
    public void execute() {
        InputManager inputManager = new InputManager();
        var client = inputManager.addClient();
        clientController.createClient(client);
    }
}
