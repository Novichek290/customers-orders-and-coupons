package dev.sorokin.repository;

import dev.sorokin.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long>{
    List<Client> findByName(String name);
    List<Client> findByEmail(String email);
    Optional findById(Long id);

}
