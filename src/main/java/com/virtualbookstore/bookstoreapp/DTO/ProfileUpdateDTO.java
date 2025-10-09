package com.virtualbookstore.bookstoreapp.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileUpdateDTO {
	
	private String name;
	private String email;

}
