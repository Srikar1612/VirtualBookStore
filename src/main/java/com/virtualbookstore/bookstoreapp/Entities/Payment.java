package com.virtualbookstore.bookstoreapp.Entities;

import java.time.LocalDateTime;

import com.virtualbookstore.bookstoreapp.enums.PaymentStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	@NotNull(message = "User is required")
	private User user;
	
	@Min(value = 0, message = "amount should not be negative")
	@Column(nullable = false)
	private Double amount;
	
	@Column(nullable = false, unique = true)
	private  String transactionId;
	
	@Column(nullable = false)
	private String paymentMethod;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private PaymentStatus paymentStatus;
	
	private LocalDateTime paymentDate;

}
