package com.example.product_api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "product")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "name")
  private String name;
  @Column(name = "description")
  private String description;
  @Column(name = "price")
  private Double price;
  @Column(name = "stock_quantity")
  private Integer stock_quantity;
  @Column(name = "store_id")
  private Long store_id;
  @Column(name = "image_url")
  private String image_url;
  @Column(name = "merchant_card_number")
  private String merchantCardNumber;
  @Column(name = "cvv")
  private String cvv;
  @Column(name = "userid")
  private Long userId;

}