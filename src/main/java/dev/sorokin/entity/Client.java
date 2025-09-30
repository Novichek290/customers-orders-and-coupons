package dev.sorokin.entity;

import dev.sorokin.util.Color;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
//@RequiredArgsConstructor
@Builder(toBuilder = true)
@Table(name = "clients", schema = "client_manager")
@Entity
public class Client {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;

    @Setter
    @Column(name = "datetime", updatable = false)
    private LocalDateTime dateTime;

    public Client(String name, String email) {
        this.name = name;
        this.email = email;
    }

    @PrePersist
    protected void onCreate() {
        dateTime = LocalDateTime.now();
    }

    @OneToOne(
            mappedBy = "client",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @ToString.Exclude
    private Profile profile;

    @OneToMany(
            mappedBy = "client",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
)
    @ToString.Exclude
    @Builder.Default
    private List<ClientOrder> order = new ArrayList<>();

public void addOrder (ClientOrder order) {
        this.order.add(order);
        order.setClient(this);
    }
    public void setEmail(String email) {
        if (!email.matches("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            throw new IllegalArgumentException("Invalid email format");
        } else this.email = email;
    }



}
