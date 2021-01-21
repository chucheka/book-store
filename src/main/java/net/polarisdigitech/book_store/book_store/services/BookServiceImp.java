package net.polarisdigitech.book_store.book_store.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.polarisdigitech.book_store.book_store.entity.Book;
import net.polarisdigitech.book_store.book_store.interfaces.BookService;
import net.polarisdigitech.book_store.book_store.repository.BookRepository;

@Service
public class BookServiceImp implements BookService{

	@Autowired
	private BookRepository bookRepository;
	
	
	@Override
	public void createOrUpdateBook(Book book) {
		
		bookRepository.save(book);
	}

	@Override
	public List<Book> getAllBook() {
		
		return bookRepository.findAll();
	}

	@Override
	public void deleteBook(Long bookId) {
		
		bookRepository.deleteById(bookId);
		
	}


	@Override
	public Book getBookByBookId(Long bookId) {
		
		return bookRepository.findById(bookId).orElse(null);
		
	}	

}
