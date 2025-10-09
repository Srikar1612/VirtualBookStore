package com.virtualbookstore.bookstoreapp.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.virtualbookstore.bookstoreapp.Entities.Book;
import com.virtualbookstore.bookstoreapp.repo.BookRepository;
import com.virtualbookstore.bookstoreapp.repo.UserRepository;

import jakarta.persistence.criteria.Predicate;


@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository, UserRepository userRepository) {

        this.bookRepository = bookRepository;

    }

    public List<Book> browseBooks(String genre, String author, Double minPrice, Double maxPrice, boolean inStock, String sortBy, String searchQuery) {

        return bookRepository.findAll((root, query, cb)->{

            List<Predicate> predicates = new ArrayList<>();
            
            if(searchQuery!=null && !searchQuery.trim().isEmpty()) {
            	
            	String lowerCaseSearch = "%"+searchQuery.toLowerCase()+"%";
            	
            	Predicate titlePredicate = cb.like(cb.lower(root.get("title")), lowerCaseSearch);
            	Predicate authorPredicate = cb.like(cb.lower(root.get("author")), lowerCaseSearch);
            	Predicate isbnPredicate = cb.like(root.get("isbn"), lowerCaseSearch);
            	
            	predicates.add(cb.or(titlePredicate, authorPredicate, isbnPredicate));
            	
            }

            if(genre!=null && !genre.isEmpty()){
                predicates.add( cb.equal(cb.lower(root.get("genre")), genre.toLowerCase()));
            }

            if(author!=null && !author.isEmpty()){
                predicates.add( cb.equal(cb.lower(root.get("author")), author.toLowerCase()));
            }

            if(minPrice!=null){
                predicates.add( cb.greaterThanOrEqualTo(root.get("price"), minPrice));
            }

            if(maxPrice!=null){
                predicates.add( cb.lessThanOrEqualTo(root.get("price"), maxPrice));
            }

            if(inStock){
                predicates.add( cb.greaterThan(root.get("stock"), 0));
            }

            final String finalSortBy = (sortBy == null || sortBy.isEmpty()) ? "title" : sortBy;

             switch (finalSortBy.toLowerCase()) {
                case "title":
                    query.orderBy(cb.asc(root.get("title")));
                    break;

                case "author":
                    query.orderBy(cb.asc(root.get("author")));
                    break;

                case "price-asc":
                    query.orderBy(cb.asc(root.get("price")));
                    break;

                case "price-desc":
                    query.orderBy(cb.desc(root.get("price")));
                    break;

                case "genre":
                    query.orderBy(cb.asc(root.get("genre")));
                    break;

                default:
                     query.orderBy(cb.asc(root.get("title")));

             }

            return cb.and(predicates.toArray(new Predicate[0]));
        });
    }
    
    public Book getBook(Long bookId) {
 
    	
    	return bookRepository.findById(bookId)
    			.orElseThrow(() -> new RuntimeException("Book not found with id: "+bookId) );
    }

    @Transactional
	public Book addNewBook(Book book) {
		
		return bookRepository.save(book);
		
	}

    @Transactional
	public Book updateBook(Long bookId, Book updatedBook) {
		// TODO Auto-generated method stub
		Optional<Book> optionalBook=bookRepository.findById(bookId);
		if(optionalBook.isPresent()) {
			
			Book book=optionalBook.get();
			book.setAuthor(updatedBook.getAuthor());
			book.setDescription(updatedBook.getDescription());
			book.setGenre(updatedBook.getGenre());
			book.setPrice(updatedBook.getPrice());
			book.setTitle(updatedBook.getTitle());
			
			return bookRepository.save(book);
			
		}
		else {
			throw new RuntimeException("Book not found");
		}
	}

    @Transactional
	public Book updateBookStock(Long bookId, int newStock) {
		
		Optional<Book> optionalBook = bookRepository.findById(bookId);
		
		if(optionalBook.isPresent()) {
			
			Book book = optionalBook.get();
			book.setStock(newStock);
			return bookRepository.save(book);
			
		} else {
			
			throw new RuntimeException("Book Not Found");
			
		}
		
	}
    
    @Transactional
    public void deleteBook(Long bookId) {
    	
    	try {
    		
    		bookRepository.deleteById(bookId);
    		
    	} catch (RuntimeException e) {
			
    		throw new RuntimeException("Book Not Found"); 
    		
		}
    	
    }
    
}
