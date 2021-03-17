package net.polarisdigitech.book_store.book_store.services;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import net.polarisdigitech.book_store.book_store.entity.Book;
import net.polarisdigitech.book_store.book_store.entity.Genre;
import net.polarisdigitech.book_store.book_store.repository.BookRepository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@ExtendWith(MockitoExtension.class)
class BookServiceImpTest {

	@Mock 
	BookRepository bookRepository;
	
	@InjectMocks
	BookServiceImp bookServiceImpl;
	
	private List<Book> books = new ArrayList<>();
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	Book book1 =  new Book();
		book1.setAuthor("Williams Shakespare");
		book1.setBookId(1l);
		book1.setTitle("Hamlet");
		book1.setPublisher("Penguins");
		book1.setCountry("England");
		book1.setGenre(Genre.POETRY);
		book1.setIsbn("ISBN128283");
		
		books.add(book1);
		
	Book book2 =  new Book();
		book2.setAuthor("Williams Shakespare");
		book2.setBookId(1l);
		book2.setTitle("Hamlet");
		book2.setPublisher("Penguins");
		book2.setCountry("England");
		book2.setGenre(Genre.FICTION);
		book2.setIsbn("ISBN128283");
		
		
		books.add(book2);
	}

	
	@Test
	@DisplayName("GET BOOK BY BOOK ID")
	void getBookByIdTest() {
		// ARRANGE
		when(bookRepository.findById(1l)).thenReturn(Optional.of(books.get(0)));
		
		
		// ACT
		Book result = bookServiceImpl.getBookByBookId(1l);
		
		// ASSERT
		assertThat(result.getBookId(),equalTo(1l));
		assertThat(result,hasProperty("genre",equalTo("Classic")));
		assertThat(result,instanceOf(Book.class));
	}
	
	@Test 
	@DisplayName("GET ALL BOOKS")
	void getAllBooksInStore() {
		
		//ARRANGE
		when(bookRepository.findAll()).thenReturn(books);
		//ACT 
		List<Book> bookList = bookServiceImpl.getAllBook();
		//ASSERT
		assertThat("The size of the list should be "+books.size(),bookList,hasSize(2));
		assertThat("Must return a List Type",bookList,instanceOf(List.class));
	}
	
	@AfterEach
	void tearDown() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

}
