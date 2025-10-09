package com.virtualbookstore.bookstoreapp.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @ManyToOne(optional = false)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;
    @Column(nullable = false)
    @Min(1)
    private int quantity;
}
