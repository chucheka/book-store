package net.polarisdigitech.book_store.book_store.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="books")
public class Book {

	 @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long bookId;
	 
	 @NotNull(message="Title is required!")
	 @Size(min = 1, max = 100, message = "Please enter between {min} and {max} characters.")
	 private String title;
	 
	 @NotNull(message="Author is required!")
	 @Size(min = 1, max = 100, message = "Please enter between {min} and {max} characters.")
	 private String author;
	 
	 @NotNull(message="ISBN is required!")
	 @Size(min = 1, max = 100, message = "Please enter between {min} and {max} characters.")
	 private String isbn;
	 
	 @NotNull(message="Publisher is required!")
	 @Size(min = 1, max = 100, message = "Please enter between {min} and {max} characters.")
	 private String publisher;
	 
	 @NotNull(message="Country is required!")
	 @Size(min = 1, max = 100, message = "Please enter between {min} and {max} characters.")
	 private String country;
	 
	 @NotNull(message="Genre is required!")
	 @Size(min = 1, max = 100, message = "Please enter between {min} and {max} characters.")
	 private String genre;

	public Book() {
		super();
	}

	public Book(String title, String author, String isbn, String publisher, String country, String genre) {
		super();
		this.title = title;
		this.author = author;
		this.isbn = isbn;
		this.publisher = publisher;
		this.country = country;
		this.genre = genre;
	}

	public Long getBookId() {
		return bookId;
	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}
	 
	 
}
