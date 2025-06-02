package dev.sorokin.service;

import dev.sorokin.design.Color;
import dev.sorokin.entity.Client;
import dev.sorokin.exeptions.ClientEmailAlreadyExists;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Service;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class ClientService {
    private final TransactionHelper transactionHelper;
    private final SessionFactory sessionFactory;

    public ClientService(TransactionHelper transactionHelper, SessionFactory sessionFactory) {
        this.transactionHelper = transactionHelper;
        this.sessionFactory = sessionFactory;
    }

    public Client create(Client client) {
        return transactionHelper.executeInTransaction(session -> {
            try{
                Long id = client.getId();
                if (id == null) {
                    session.persist(client);
                    return client;
                }
            } catch (ConstraintViolationException e) {
                Color color = new Color();
                if ("23505".equals(e.getSQLState())) {  // Код ошибки уникальности в PostgreSQL
                    String constraintName = e.getConstraintName(); // Например: "users_email_key"
                    throw new ClientEmailAlreadyExists(parseFieldName(constraintName),
                            color.getPURPLE() + "The " + client.getEmail() + " you entered is already in use" + color.getRESET());
                }
            }
            return client;
        });
    }


        private String parseFieldName(String constraintName) {
            if (constraintName.contains("email")) return "email";
            if (constraintName.contains("username")) return "username";
            return "unknown_field";
        }

    public void delete (Long id) {
        transactionHelper.executeInTransaction(session -> {
            Client clientToDelete = session.get(Client.class, id);
            session.remove(clientToDelete);
        });
    }

    public List<Client> getAll() {
        List<Client> clientList = transactionHelper.executeInTransaction(session -> {
            return session.createQuery("SELECT c FROM Client c").getResultList();
        });
        return clientList;
    }

    public void printAll() {
        List<Client> clientList = transactionHelper.executeInTransaction(session -> {
            return session.createQuery("SELECT c FROM Client c").getResultList();
        });

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        System.out.println("-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-");
        for(Client clients : clientList) {
            System.out.printf("ID: %-12s | Name: %-12s | Email: %-26s | At: %-12s\n",
                    clients.getId(),
                    clients.getName(),
                    clients.getEmail(),
                    clients.getDateTime() != null ? clients.getDateTime().format(formatter) : "null");
        }
        System.out.println("-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-");
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

    public void update (Long id, String newName, String newEmail) {
        transactionHelper.executeInTransaction(session -> {
            session.createQuery("UPDATE Client c SET c.name = :name, c.email = :email where c.id = :id")
                    .setParameter("name", newName)
                    .setParameter("email", newEmail)
                    .setParameter("id", id)
                    .executeUpdate();
            return null;
        });
    }

}
