package com.virtualbookstore.bookstoreapp.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.virtualbookstore.bookstoreapp.Entities.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {
    List<Book> findAll();
    
//    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Book> findById(Long id);

}
