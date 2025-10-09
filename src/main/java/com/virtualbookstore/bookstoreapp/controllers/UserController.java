package com.virtualbookstore.bookstoreapp.controllers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.virtualbookstore.bookstoreapp.DTO.PasswordUpdateDTO;
import com.virtualbookstore.bookstoreapp.DTO.ProfileUpdateDTO;
import com.virtualbookstore.bookstoreapp.DTO.UpdateRoleDTO;
import com.virtualbookstore.bookstoreapp.Entities.User;
import com.virtualbookstore.bookstoreapp.Services.UserService;
import com.virtualbookstore.bookstoreapp.Util.GetIdUtility;

@RestController
@RequestMapping("api/user/profile")
public class UserController {

    private final UserService userService;
    private final GetIdUtility getIdUtility;
	
	public UserController(UserService userService, GetIdUtility getIdUtility) {
		
		this.userService=userService;
		this.getIdUtility=getIdUtility;
		
	}
	
	@GetMapping
	public ResponseEntity<?> getUser() {
		
		try {
			
			Long userId = getIdUtility.getCurrentUserId();
			User user = userService.getUser(userId);
			return new ResponseEntity<>(user, HttpStatus.OK);
			
		} catch(RuntimeException e) {
			
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
			
		}
		
	}
	
	@PutMapping("/update")
	public ResponseEntity<?> updateProfile(
			ProfileUpdateDTO profileUpdateDTO
			) {
		
		try {
			
			Long userId = getIdUtility.getCurrentUserId();
			User updatedUser = userService.updateUserProfile(userId, profileUpdateDTO);
			
			return new ResponseEntity<>(updatedUser, HttpStatus.OK);
			
		} catch(RuntimeException e) {
			
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
			
		}
		
	}
	
	@PatchMapping("/update")
	public ResponseEntity<?> updatePassword(
			@RequestBody PasswordUpdateDTO passwordUpdateDTO
			) {
		
		try {
			
			Long userId = getIdUtility.getCurrentUserId();
			User updatedUser = userService.updateUserPassword(userId, passwordUpdateDTO);
			
			return new ResponseEntity<>(updatedUser, HttpStatus.OK);
			
		} catch(RuntimeException e) {
			
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
			
		}
		
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<?> deleteUser(){
		
		
		try {
			
			Long userId = getIdUtility.getCurrentUserId();
			userService.deleteUserProfile(userId);
			
			return new ResponseEntity<>("Account deleted successfully", HttpStatus.OK);
			
		} catch (Exception e) {
			
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
			
		}
		
	}
	
	@PutMapping("/updaterole")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> updateRole(
			@RequestBody UpdateRoleDTO updateRoleDTO
			) {
		
		try {
			
			User user= userService.updateUserRole(updateRoleDTO);
			return new ResponseEntity<>(user, HttpStatus.OK);
			
		} catch (Exception e) {
			
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
			
		}
		
	}
	
//	private Long getCurrentUserId(){
//    	
//    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//    	
//    	if(authentication==null || !authentication.isAuthenticated() || "AnonymousUser".equals(authentication.getPrincipal())) {
//    		
//    		throw new RuntimeException("User is not authenticated");
//    		
//    	}
//    	
//    	String email = authentication.getName();
//    	User user = userService.findByEmail(email);
//    	Long userId = user.getId();
//    	
//    	return userId;
//    	
//    }
//
}
