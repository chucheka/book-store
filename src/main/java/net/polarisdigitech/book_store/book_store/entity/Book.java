package net.polarisdigitech.book_store.book_store.entity;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name="books")
public class Book {

	 @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long bookId;
	 
	 @NotNull(message="Title is required!")
	 @Size(min = 1, max = 4, message = "Please enter between {min} and {max} characters.")
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
	 
	 @ColumnDefault(value="'Nigera'")
	 private String country;
	 
	 @Enumerated(EnumType.STRING)
	 private Genre genre;
	 
	 @OneToMany(mappedBy="book",cascade=CascadeType.ALL,orphanRemoval=true)
	 private Set<Review> reviews = new HashSet<>();

	public Book() {
		super();
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



	public Genre getGenre() {
		return genre;
	}



	public void setGenre(Genre genre) {
		this.genre = genre;
	}



	public Set<Review> getReviews() {
		return reviews;
	}



	public void setReviews(Set<Review> reviews) {
		this.reviews = reviews;
	}



	public Book(
			@NotNull(message = "Title is required!") @Size(min = 1, max = 4, message = "Please enter between {min} and {max} characters.") String title,
			@NotNull(message = "Author is required!") @Size(min = 1, max = 100, message = "Please enter between {min} and {max} characters.") String author,
			@NotNull(message = "ISBN is required!") @Size(min = 1, max = 100, message = "Please enter between {min} and {max} characters.") String isbn,
			@NotNull(message = "Publisher is required!") @Size(min = 1, max = 100, message = "Please enter between {min} and {max} characters.") String publisher,
			String country, Genre genre, Set<Review> reviews) {
		super();
		this.title = title;
		this.author = author;
		this.isbn = isbn;
		this.publisher = publisher;
		this.country = country;
		this.genre = genre;
		this.reviews = reviews;
	}



	public void addReview(Review review) {
		reviews.add(review);
		review.setBook(this);
	}
	
	public void removeReview(Review review) {
		reviews.remove(review);
		review.setBook(null);
	}
	 
	@Override
	public boolean equals(Object o) {
		if(this==o) return true;
		if(!(o instanceof Book)) return false;
		
		return Objects.equals(bookId, ((Book)o).bookId);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(bookId);
	}
}
