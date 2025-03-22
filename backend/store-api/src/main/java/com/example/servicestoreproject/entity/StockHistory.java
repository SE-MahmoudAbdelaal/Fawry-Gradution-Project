package com.example.servicestoreproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Entity
@Table(name = "stockhistory")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StockHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Column(name = "StoreHistoryid")
    private Long stockhistoryid;
    @Column(name = "type")
    private String type;
    @Column(name = "quantitychange")
    private int QuantityChange;
    @Column(name = "timestamp")
    private LocalDate TimeStamp;
    @Column(name = "productid")
    private Long productId;
}
