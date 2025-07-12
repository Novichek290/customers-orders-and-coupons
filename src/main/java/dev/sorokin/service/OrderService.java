package dev.sorokin.service;

import dev.sorokin.entity.Client;
import dev.sorokin.entity.ClientOrder;
import dev.sorokin.repository.ClientRepository;
//import dev.sorokin.util.TransactionHelper;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final ClientRepository clientRepository;
//    private final TransactionHelper transactionHelper;

    public void orderCreate(ClientOrder order) {
//        clientRepository.save(order);
//        transactionHelper.executeInTransaction(session -> {
//            session.persist(order);
//            return order;
//        });
    }

//    public List<ClientOrder> getClientOrders (Long clientId) {
//        return transactionHelper.executeInTransaction(session -> {
//            return session.createQuery("select o from ClientOrder o where o.client.id =: clientId", ClientOrder.class)
//                    .setParameter("clientId", clientId)
//                    .getResultList();
//        });
//    }
}
