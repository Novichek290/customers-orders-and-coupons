package dev.sorokin.service;

import dev.sorokin.exeption.ServiceExeption;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;
import java.util.function.Function;

@Component
public class TransactionHelper {
    private final SessionFactory sessionFactory;

    public TransactionHelper(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    ///Consumer (save, update, logging)
    public void executeInTransaction(Consumer<Session> action) {
        Transaction transaction = null;

        try (Session session = sessionFactory.openSession()) {
            transaction = session.getTransaction();
            transaction.begin();

            action.accept(session);

            transaction.commit();
        } catch (ServiceExeption e) {
            if (transaction != null) {
                transaction.rollback();
            }

            throw new ServiceExeption("Не удалось добавить студентика", e);
        }
    }

    ///Function (read, transform, calculate)
    public <T> T executeInTransaction(Function<Session, T> action) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {

            transaction = session.getTransaction();
            transaction.begin();

            if(transaction == null) throw new IllegalStateException("Transaction not started");
            var result = action.apply(session);
            transaction.commit();

            return result;
        } catch (ServiceExeption e) {
            if(transaction != null) {
                transaction.rollback();
            }
            throw  new ServiceExeption("Не удалось добавить студентика", e);
        }
    }
}
