package com.virtualbookstore.bookstoreapp.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.virtualbookstore.bookstoreapp.Entities.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>, JpaSpecificationExecutor<Order> {

	List<Order> findByUserId(Long userId);
	
	
	
}
