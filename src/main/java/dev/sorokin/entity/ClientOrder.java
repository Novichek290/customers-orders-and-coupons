package dev.sorokin.entity;

import dev.sorokin.controller.DBPrintout;
import dev.sorokin.service.UniversalService;
import dev.sorokin.util.OrderStatus;
//import dev.sorokin.util.TransactionHelper;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@ToString
//@AllArgsConstructor
//@NoArgsConstructor
@RequiredArgsConstructor
//@Builder(toBuilder = true)
@Table(name = "client_orders", schema = "client_manager")
@Entity
public class ClientOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @Column(name = "order_date", columnDefinition = "TIMESTAMP")
    private LocalDateTime orderDate;

    @Column(name = "order_amount", columnDefinition = "SMALLINT")
    private double totalAmount;

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = UniversalService.rounder(totalAmount, 2);
    }

    @Column(name = "order_status", length = 20)
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    @ToString.Exclude
    private Client client;

}
