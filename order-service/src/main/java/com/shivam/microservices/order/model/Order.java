package com.shivam.microservices.order.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name = "orders")
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String OrderNumber;
    private String skuCode;
    private BigDecimal price;
    private Integer quantity;
}
