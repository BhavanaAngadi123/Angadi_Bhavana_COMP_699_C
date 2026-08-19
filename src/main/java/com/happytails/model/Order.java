package com.happytails.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Getter @Setter @NoArgsConstructor
public class Order {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @ManyToOne(optional = false) @JoinColumn(name = "product_id") private Product product;
    @ManyToOne(optional = false) @JoinColumn(name = "buyer_id") private User buyer;
    @Column(nullable = false) private Integer quantity;
    @Column(name = "total_price", nullable = false, precision = 10, scale = 2) private BigDecimal totalPrice;
    private String status = "cart";
    @Column(name = "payment_method") private String paymentMethod;
    @Column(name = "created_at") private LocalDateTime createdAt = LocalDateTime.now();
}
