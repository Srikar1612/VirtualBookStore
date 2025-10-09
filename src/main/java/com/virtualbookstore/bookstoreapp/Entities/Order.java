package com.virtualbookstore.bookstoreapp.Entities;

import com.virtualbookstore.bookstoreapp.common.Auditable;
import com.virtualbookstore.bookstoreapp.enums.OrderStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "orders")
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
    private List<OrderItem> orderItem;
    @Min(0)
    private  Double totalPrice;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus status;
    private Long paymentId;
}
