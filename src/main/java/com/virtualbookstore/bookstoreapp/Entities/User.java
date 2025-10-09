package com.virtualbookstore.bookstoreapp.Entities;

import com.virtualbookstore.bookstoreapp.common.Auditable;
import com.virtualbookstore.bookstoreapp.enums.Role;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.Data;

@EqualsAndHashCode(callSuper=false)
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String name;
    @Email
    @NotBlank
    @Column(unique = true, nullable = false)
    private String email;
    @NotBlank
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;
}
