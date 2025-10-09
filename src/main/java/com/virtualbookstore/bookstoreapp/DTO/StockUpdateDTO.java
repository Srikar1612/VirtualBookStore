package com.virtualbookstore.bookstoreapp.DTO;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockUpdateDTO {

	@Min(0)
	private int stock;
	
}
