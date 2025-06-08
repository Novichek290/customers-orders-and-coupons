package dev.sorokin.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Entity
@Table(name = "profile", schema = "client_manager")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    public Profile(String address, String phone, Client client) {
        this.address = address;
        this.phone = phone;
        this.client = client;
    }

    @Column(name = "address")
    private String address;
    @Column(name = "phone")
    private String phone;

    @OneToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Client client;
}
