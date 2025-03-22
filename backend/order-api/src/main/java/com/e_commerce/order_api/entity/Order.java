package com.e_commerce.order_api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
@Builder
public class Order {
    @Id
    @NonNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id", unique = true)
    private Long id;
    private String card_number;
    private String cvv;
    @Column (name = "total_amount")
    @NotNull
    private double total_amount;
    private double  total_amount_after_discount;
    @Column (name = "transaction_id")
    private Long transactionId;
    @Column (name = "customer_id")
    @NotNull
    private Long customerId;
    @Column (name = "ordered_date")
    @NotNull
    private LocalDate createdAt;
    @Column(name = "status")
    @NotNull
    private String status;
    @NotNull(message = "You should add items")
    @ManyToMany (cascade = CascadeType.ALL)
    @JoinTable (name = "cart" , joinColumns = @JoinColumn(name = "order_id"), inverseJoinColumns = @JoinColumn (name = "item_id"))
    private List<Item> items;
    @ManyToOne (cascade = CascadeType.ALL)
    @JoinColumn (name = "user_id")
    private User user;

}
