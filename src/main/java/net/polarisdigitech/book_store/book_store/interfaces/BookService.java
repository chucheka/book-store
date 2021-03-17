package net.polarisdigitech.book_store.book_store.interfaces;

import java.util.List;

import net.polarisdigitech.book_store.book_store.entity.Book;

public interface BookService {

	public Book createOrUpdateBook(Book book);
	 
    public List<Book> getAllBook();

    public String deleteBook(Long bookId);
    
    public Book getBookByBookId(Long bookId);
    
}
