package dev.sorokin.controller;

import dev.sorokin.util.Color;
import dev.sorokin.entity.Client;
import dev.sorokin.util.TransactionHelper;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class Console {
    private final TransactionHelper transactionHelper;

    public Console(TransactionHelper transactionHelper) {
        this.transactionHelper = transactionHelper;
    }

    public List<Client> getAll() {
        return transactionHelper.executeInTransaction(session -> {
            return session.createQuery("SELECT c FROM Client c").getResultList();
        });
    }

    public void printAll() {
        try {
            List<Client> clientList = transactionHelper.executeInTransaction(session -> {
                return session.createQuery("SELECT c FROM Client c order by id").setTimeout(3).getResultList();
            });

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
            System.out.print(Color.getBLUE() + "-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-");
            System.out.println("-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-" + Color.getRESET());
            for (Client clients : clientList) {
                System.out.printf("ID: %-12s | Name: %-12s | Email: %-26s | At: %-26s | profile: %-12s\n",
                        clients.getId(),
                        clients.getName(),
                        clients.getEmail(),
                        clients.getDateTime() != null ? clients.getDateTime().format(formatter) : "null",
                        clients.getProfile() != null ? clients.getProfile().getId() : null);
            }
            System.out.print(Color.getBLUE() + "-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-");
            System.out.println("-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-" + Color.getRESET());
        } catch (Exception e) {
            System.err.println(Color.getCYAN() + "Ошибка при получении клиентов" + Color.getRESET());
            throw e;

        }
    }

    public Client getById(Long id) {
        return transactionHelper.executeInTransaction(session -> {
            return session.get(Client.class, id);
        });
    }

    public List<Client> getByName(String name) {
        return transactionHelper.executeInTransaction(session -> {
            return session.createQuery("SELECT c FROM Client c where c.name = :name", Client.class)
                    .setParameter("name", name).
                    getResultList();
        });
    }

    public List<Client> getByEmail(String email) {
        return transactionHelper.executeInTransaction(session -> {
            return session.createQuery("SELECT c FROM Client c where c.email = :email", Client.class)
                    .setParameter("email", email).
                    getResultList();
        });
    }
}
