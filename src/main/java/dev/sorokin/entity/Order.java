package dev.sorokin.entity;

import dev.sorokin.util.OrderStatus;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Table(name = "client_orders", schema = "client_manager")
@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "orderdate", columnDefinition = "TIMESTAMP")
    private LocalDateTime orderDate;

    @Column(name = "orderamount", columnDefinition = "SMALLINT")
    private int totalAmount;

    @Column(name = "status", length = 20)
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    @ToString.Exclude
    private Client client;

}
