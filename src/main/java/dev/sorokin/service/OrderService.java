package dev.sorokin.service;

import dev.sorokin.entity.Order;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private final TransactionHelper transactionHelper;

    public OrderService(TransactionHelper transactionHelper) {
        this.transactionHelper = transactionHelper;
    }

    public void orderCreate(Order order) {
        transactionHelper.executeInTransaction(session -> {
            session.persist(order);
            return order;
        });
    }

    public List<Order> getClientOrders (Long clientId) {
        return transactionHelper.executeInTransaction(session -> {
            return session.createQuery("select o from Order o where o.client.id =: clientId", Order.class)
                    .setParameter("clientId", clientId)
                    .getResultList();
        });
    }
}
