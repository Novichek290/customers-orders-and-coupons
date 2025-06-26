package dev.sorokin.service;

import dev.sorokin.entity.Client;
import dev.sorokin.entity.ClientOrder;
import dev.sorokin.util.TransactionHelper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private final TransactionHelper transactionHelper;

    public OrderService(TransactionHelper transactionHelper) {
        this.transactionHelper = transactionHelper;
    }

    public void orderCreate(ClientOrder order) {
        transactionHelper.executeInTransaction(session -> {
            session.persist(order);
            return order;
        });
    }

    public List<ClientOrder> getClientOrders (Long clientId) {
        return transactionHelper.executeInTransaction(session -> {
            return session.createQuery("select o from ClientOrder o where o.client.id =: clientId", ClientOrder.class)
                    .setParameter("clientId", clientId)
                    .getResultList();
        });
    }
}
