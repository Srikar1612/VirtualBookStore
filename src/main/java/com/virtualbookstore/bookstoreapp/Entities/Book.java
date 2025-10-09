package com.virtualbookstore.bookstoreapp.Entities;

import com.virtualbookstore.bookstoreapp.common.Auditable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;	
    @Column(nullable = false)
    private String title;
    private String author;
    private String genre;
    @Column(unique = true)
    private String isbn;
    @Size(min = 1, max = 200)
    private String description;
    @Min(0)
    private double price;
    @Min(0)
    private int stock;
    @Min(0)
    @Max(5)
    private double rating;
}
