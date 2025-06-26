package dev.sorokin.service;

import dev.sorokin.util.Color;
import dev.sorokin.entity.Client;
import dev.sorokin.entity.Profile;
import dev.sorokin.exeption.ClientEmailAlreadyExists;
import dev.sorokin.util.TransactionHelper;
import lombok.AllArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ClientService {
    private final TransactionHelper transactionHelper;

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
                            Color.getPURPLE() + "The " + client.getEmail() + " you entered is already in use" + color.getRESET());
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
        return transactionHelper.executeInTransaction(session -> {
            return session.createQuery("SELECT c FROM Client c").getResultList();
        });
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

    public void clientUpdate(Long id, String newName, String newEmail) {
        transactionHelper.executeInTransaction(session -> {
            session.createQuery("UPDATE Client c SET c.name = :name, c.email = :email where c.id = :id")
                    .setParameter("name", newName)
                    .setParameter("email", newEmail)
                    .setParameter("id", id)
                    .executeUpdate();
            return null;
        });
    }

    public Client profileUpdate(Long clientId, String address, String phone) {
        Client client = getById(clientId);
        Profile profile = Profile.builder()
                .phone(phone)
                .address(address)
                .build();

        client.setProfile(profile);
        profile.setClient(client);

        return client;
    }

    public void clientUpdate(Client client) {
        transactionHelper.executeInTransaction(session -> {

            session.merge(client);
        });
    }

    public void addProfileToClient(Client client, Profile profile) {
        transactionHelper.executeInTransaction(session -> {
            client.setProfile(profile);
            profile.setClient(client);
            session.merge(client);
        });
    }
}
