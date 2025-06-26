package dev.sorokin.controller;

import dev.sorokin.entity.ClientOrder;
import dev.sorokin.service.OrderService;
import dev.sorokin.util.Color;
import dev.sorokin.entity.Client;
import dev.sorokin.util.TransactionHelper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
@AllArgsConstructor
public class DBPrintout {
    private final TransactionHelper transactionHelper;

    public void printAll() {
        try {
            List<Client> clientList = transactionHelper.executeInTransaction(session -> {
                return session.createQuery("SELECT c FROM Client c order by id").setTimeout(3).getResultList();
            });

            if(clientList.isEmpty()){
                Console console = new Console();
                border();
                console.notification("DB is empty");
                border();
                return;
            }
            OrderService orderService = new OrderService(transactionHelper);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
            border();
            for (Client clients : clientList) {
                System.out.printf("ID: %-12s | Name: %-12s | Email: %-26s | At: %-26s | Profile: %-12s | Order: %-12s\n",
                        clients.getId(),
                        clients.getName(),
                        clients.getEmail(),
                        clients.getDateTime() != null ? clients.getDateTime().format(formatter) : "null",
                        clients.getProfile() != null ? clients.getProfile().getId() : null,
                        clients.getOrder() != null ? orderService.getClientOrders(clients.getId()).size() : null
                        );
            }
            border();
        } catch (Exception e) {
            System.err.println(Color.getCYAN() + "Ошибка при получении клиентов" + Color.getRESET());
            throw e;
        }
    }

    private void border() {
        System.out.print(Color.getBLUE() + "-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-");
        System.out.println("-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-" + Color.getRESET());
    }
}
