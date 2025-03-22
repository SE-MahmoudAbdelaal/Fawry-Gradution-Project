package com.example.servicestoreproject.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "store")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@SequenceGenerator(name = "store_seq", sequenceName = "store_seq", allocationSize = 1)
    @Column(name = "store_id")
    private Long storeid;
    @Column(name = "name")
    private String name;
    @Column(name = "address")
    private String address;

}
