package com.virtualbookstore.bookstoreapp.Services;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.virtualbookstore.bookstoreapp.Entities.Book;
import com.virtualbookstore.bookstoreapp.Entities.CartItem;
import com.virtualbookstore.bookstoreapp.Entities.User;
import com.virtualbookstore.bookstoreapp.repo.BookRepository;
import com.virtualbookstore.bookstoreapp.repo.CartItemRepository;
import com.virtualbookstore.bookstoreapp.repo.UserRepository;

@Service
public class CartItemService {
	
	private final CartItemRepository cartItemRepository;
	private final BookRepository bookRepository;
	private final UserRepository userRepository;
	
	public CartItemService(CartItemRepository cartItemRepository, BookRepository bookRepository, UserRepository userRepository) {
		
		this.cartItemRepository=cartItemRepository;
		this.bookRepository=bookRepository;
		this.userRepository=userRepository;
		
	}
	
	public List<CartItem> getCart(Long userId) {
			
			return cartItemRepository.findByUserId(userId);
			
		}

	@Transactional
	public CartItem addCartItem(Long userId, Long bookId, int quantity) {
		
		if(quantity<1)
			throw new IllegalArgumentException("Quantity should be always greater than 1");
		
		User user=userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
		Book book=bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));
		
		Optional<CartItem> cartItemOptional = cartItemRepository.findByUserIdAndBookId(userId, bookId);
		
		if(cartItemOptional.isPresent()) {
			
			CartItem cartItem = cartItemOptional.get();
			cartItem.setQuantity(cartItem.getQuantity() + quantity);
			return cartItemRepository.save(cartItem);
			
		} else {
			
			CartItem cartItem = CartItem.builder()
					.user(user)
					.book(book)
					.quantity(quantity)
					.build();
			return cartItemRepository.save(cartItem);
			
		}
		
	}

	@Transactional
	public CartItem updateQuantity(Long cartItemId, Integer newQuantity) {
		
		if(newQuantity<1)
			throw new IllegalArgumentException("Quantity shoul always be greater than 1");
		
		Optional<CartItem> cartItemOptional = cartItemRepository.findById(cartItemId);
		
		if(cartItemOptional.isPresent()) {
			
			CartItem cartItem = cartItemOptional.get();
			cartItem.setQuantity(newQuantity);
			return cartItemRepository.save(cartItem);
			
		} else {
			
			throw new RuntimeException("Cart Item not found");
			
		}
	}

	@Transactional
	public void deleteCartItem(Long userId, Long cartItemId) {
		
		CartItem cartItem= cartItemRepository.findByIdAndUserId(cartItemId, userId).orElseThrow(()-> new RuntimeException("CartItem Not found"));
		
		cartItemRepository.delete(cartItem);

	}

	@Transactional
	public void clearCart(Long userId) {
		
		List<CartItem> cartItems=cartItemRepository.findByUserId(userId);
		if(!cartItems.isEmpty())
			cartItemRepository.deleteAll(cartItems);
		else {
			
			throw new RuntimeException("cart is empty");
			
		}
		
	}

}
