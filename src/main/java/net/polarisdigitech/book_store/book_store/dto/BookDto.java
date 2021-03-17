package net.polarisdigitech.book_store.book_store.dto;

import net.polarisdigitech.book_store.book_store.entity.Genre;

public class BookDto {

	private String title;
	 
	 private String author;
	 
	 private String isbn;
	 
	 private String publisher;
	 
	 private String country;
	 
	 private Genre genre;

	public BookDto() {
		super();
	}

	public BookDto(String title, String author, String isbn, String publisher, String country, Genre genre) {
		super();
		this.title = title;
		this.author = author;
		this.isbn = isbn;
		this.publisher = publisher;
		this.country = country;
		this.genre = genre;
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

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	
	 
}
