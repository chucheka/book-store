package net.polarisdigitech.book_store.book_store.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import net.polarisdigitech.book_store.book_store.entity.Book;
import net.polarisdigitech.book_store.book_store.interfaces.BookService;

@Controller
public class BookController {

	@Autowired 
	private BookService bookService;
	
	@GetMapping({"/","home"})
	public String getHomePage() {
		
		return "home";
	}
	
	@GetMapping("/book/list")
	public ModelAndView getBookList() {
		ModelAndView model = new ModelAndView();
		
		List<Book> bookList = bookService.getAllBook();
		model.addObject(bookList);
		model.setViewName("book-list");
		return model;
	}
	
	@GetMapping("/book/addBook")
	public ModelAndView getAddBookForm() {
		ModelAndView model = new ModelAndView();
		  
		  Book book = new Book();
		  model.addObject("book", book);
		  model.setViewName("book-form");
		  
		  return model;
	}
	
	@PostMapping("/book/addBook")
	public ModelAndView addBook(@ModelAttribute("book") Book book) {
		
		bookService.createOrUpdateBook(book);
		return new ModelAndView("redirect:/book/list");
	}
	
	@GetMapping("/book/{bookId}")
	public ModelAndView viewBook(@PathVariable("bookId") long bookId) {
		ModelAndView model = new ModelAndView();
		Book book = bookService.getBookByBookId(bookId);
		model.setViewName("book");
		model.addObject(book);
		model.addObject(bookId);
		return model;
	}
	@GetMapping("/updatebook/{bookId}")
	public ModelAndView updateBook(@PathVariable("bookId") long bookId) {
		ModelAndView model = new ModelAndView();
		  
		  Book book = bookService.getBookByBookId(bookId);
		  model.addObject("book", book);
		  model.setViewName("book-form");
		  
		  return model;
	}
	@GetMapping("/book/deleteBook/{bookId}")
	public ModelAndView deleteABook(@PathVariable("bookId") long bookId) {
		bookService.deleteBook(bookId);
		return new ModelAndView("redirect:/book/list");
		
	}
}
