package dev.sorokin.service.operation;

import dev.sorokin.repository.ClientRepository;
import dev.sorokin.entity.Client;
import dev.sorokin.exception.ClientEmailAlreadyExists;
import dev.sorokin.util.Color;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Service;

//Проверяет данные (валидация email, имени).
//Добавляет дату создания.
//Передаёт в репозиторий.
@Service
@RequiredArgsConstructor
public class ClientCreateOperation {
    private ClientRepository clientRepository;

    @Transactional
    public Client create(Client client) {
            try{
                Long id = client.getId();
                if (id == null) {
                    clientRepository.save(client);
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
    }

    private String parseFieldName(String constraintName) {
        if (constraintName.contains("email")) return "email";
        if (constraintName.contains("username")) return "username";
        return "unknown_field";
    }
}
