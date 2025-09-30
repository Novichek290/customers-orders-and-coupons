package dev.sorokin.service.contoller;

import dev.sorokin.entity.Client;
import dev.sorokin.service.operation.ClientCreateOperation;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

//    Принимает данные от command
//    Передаёт их в сервис.
@Service
@RequiredArgsConstructor
public class ClientController {
    private ClientCreateOperation clientCreateOperation;

    @Transactional
    public  void createClient(Client client) {
        clientCreateOperation.create(client);
    }
}
