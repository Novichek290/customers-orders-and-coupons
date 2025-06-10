package dev.sorokin.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.core.SpringVersion;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Table(name = "clients", schema = "client_manager")
@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;

    @Setter
    private LocalDateTime dateTime;

    @OneToOne(
            mappedBy = "client",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @ToString.Exclude
    private Profile profile;


    public void setEmail(String email) {
        if (!email.matches("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            throw new IllegalArgumentException("Invalid email format");
        } else this.email = email;
    }

}
