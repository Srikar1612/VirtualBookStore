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
public class Review extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id",  nullable = false)
    private User user;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "book_id",  nullable = false)
    private Book book;
    @Column(nullable = false)
    @Min(0)
    @Max(5)
    private Double rating;
    @Column(length = 200)
    @Size(max = 200)
    private String comment;
}
