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
@Table(name = "clients")
@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;

    private LocalDateTime dateTime;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_id")
    private Profile profile;

    public Client(String name, String email){
        this.name = name;
        this.email = email;

    }
}
