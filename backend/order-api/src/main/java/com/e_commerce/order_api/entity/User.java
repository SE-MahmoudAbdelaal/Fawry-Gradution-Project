package com.e_commerce.order_api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String email;
    @Column(name = "user_name")
    @NotNull
    private String firstName;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Order> orders;
}

