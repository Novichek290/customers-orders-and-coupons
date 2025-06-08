package dev.sorokin.service;

import dev.sorokin.entity.Profile;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    private final SessionFactory sessionFactory;
    private final TransactionHelper transactionHelper;

    public ProfileService(SessionFactory sessionFactory,
                          TransactionHelper transactionHelper
    ) {
        this.sessionFactory = sessionFactory;
        this.transactionHelper = transactionHelper;
    }

    public Profile profileCreate(Profile profile) {
        return transactionHelper.executeInTransaction(session -> {
            session.persist(profile);
            return profile;
        });
    }

    public void profileDelete(Long id) {
            transactionHelper.executeInTransaction(session -> {
            Profile profileToDelete = session.get(Profile.class, id);
            session.remove(profileToDelete);
            });
    }

    public Profile profileGet(Long id) {
        return transactionHelper.executeInTransaction(session -> {
            Profile getProfile = session.get(Profile.class, id);
            return getProfile;
        });
    }
}
