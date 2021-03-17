package net.polarisdigitech.book_store.book_store.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import net.polarisdigitech.book_store.book_store.dto.BookDto;
import net.polarisdigitech.book_store.book_store.entity.Book;
import net.polarisdigitech.book_store.book_store.interfaces.BookService;

@RestController
@RequestMapping("/api/v1/books")
public class BookAPIController {

	@Autowired
	BookService bookService;
	
	@GetMapping("/{bookId}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@ResponseStatus(HttpStatus.OK)
	public Book getBookById(@PathVariable Long bookId) {
		
		
		return bookService.getBookByBookId(bookId);
	}
	
	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	public Book createABook(@RequestBody BookDto dto) {
		Book book = new Book();
		
		book.setAuthor(dto.getAuthor());
		book.setTitle(dto.getTitle());
		book.setPublisher(dto.getPublisher());
		book.setGenre(dto.getGenre());
		book.setIsbn(dto.getIsbn());
		
		return bookService.createOrUpdateBook(book);
	}
	
	@GetMapping("")

	@ResponseStatus(HttpStatus.OK)
	public List<Book> findAllBooks(){
		return bookService.getAllBook();
	}
		
	@DeleteMapping("/{bookId}")
	public String deleteBook(@PathVariable Long bookId) {
		
		return bookService.deleteBook(bookId);
	}
}
