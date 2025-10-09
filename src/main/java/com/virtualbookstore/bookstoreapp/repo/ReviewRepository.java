package com.virtualbookstore.bookstoreapp.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.virtualbookstore.bookstoreapp.Entities.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long>, JpaSpecificationExecutor<Review> {

	List<Review> findAllByBookId(Long bookId);

	Review findByIdAndBookId(Long reviewId, Long bookId);

}
