package com.virtualbookstore.bookstoreapp.Services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.virtualbookstore.bookstoreapp.Entities.Book;
import com.virtualbookstore.bookstoreapp.Entities.Review;
import com.virtualbookstore.bookstoreapp.repo.BookRepository;
import com.virtualbookstore.bookstoreapp.repo.ReviewRepository;

@Service
public class ReviewService {
	
	private final ReviewRepository reviewRepository;
	private final BookRepository bookRepository;
	
	public ReviewService(ReviewRepository reviewRepository, BookRepository bookRepository) {
		
		this.reviewRepository=reviewRepository;
		this.bookRepository=bookRepository;
		
	}

	public List<Review> retriveAllReviews(Long bookId) {
		
		Book book = bookRepository.findById(bookId).orElseThrow(()-> new RuntimeException("No book found with id: "+ bookId));
		
		return reviewRepository.findAllByBookId(book.getId());
		
	}

	public Review retriveReview(Long bookId, Long reviewId) {
		
		Book book= bookRepository.findById(bookId).orElseThrow(()-> new RuntimeException("No book found with id: "+ bookId));
		
		return reviewRepository.findByIdAndBookId(reviewId, book.getId());
		
	}

	@Transactional
	public Review createReview(Review review) {
		
		return reviewRepository.save(review);
		
	}

}
