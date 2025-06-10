package dev.sorokin.service;

import dev.sorokin.design.Color;
import dev.sorokin.entity.Client;
import dev.sorokin.entity.Profile;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    private static final Logger log = LoggerFactory.getLogger(ProfileService.class);
    private final SessionFactory sessionFactory;
    private final TransactionHelper transactionHelper;

    public ProfileService(SessionFactory sessionFactory,
                          TransactionHelper transactionHelper, Color color
    ) {
        this.sessionFactory = sessionFactory;
        this.transactionHelper = transactionHelper;
    }

    public void profileCreate(Client client, Profile profile) {
        transactionHelper.executeInTransaction(session -> {
            try {
                Long clientId = client.getId();
                if (clientId != null) {
                    System.out.println(Color.getYELLOW() + "client_id is: " + Color.getCYAN() + clientId + Color.getRESET());
                    session.persist(String.valueOf(client), profile);
//                    return profile;
                } else {
                    System.out.println(Color.getYELLOW() + "client_id is null" + Color.getRESET());
                }
            } catch (NullPointerException e) {
                log.error("e: ", e);
            }
//            return profile;
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
