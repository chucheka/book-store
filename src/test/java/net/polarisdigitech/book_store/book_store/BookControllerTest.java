package net.polarisdigitech.book_store.book_store;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.instanceOf;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import net.polarisdigitech.book_store.book_store.config.CustomJWTAuthenticationEntryPoint;
import net.polarisdigitech.book_store.book_store.config.JWTAccessDeniedHandler;
import net.polarisdigitech.book_store.book_store.config.JwtTokenProvider;
import net.polarisdigitech.book_store.book_store.controller.BookAPIController;
import net.polarisdigitech.book_store.book_store.entity.Book;
import net.polarisdigitech.book_store.book_store.entity.Genre;
import net.polarisdigitech.book_store.book_store.services.BookServiceImp;
import net.polarisdigitech.book_store.book_store.services.CustomUserDetailsService;

@WebMvcTest(BookAPIController.class)
@Disabled()
class BookControllerTest {

	@MockBean
	private JWTAccessDeniedHandler jwtAccessDeniedHandler;
	
	@MockBean
	private CustomJWTAuthenticationEntryPoint customJWTAuthenticationEntryPoint;
	
	@MockBean
	CustomUserDetailsService customUserDetailsService;
	
	@MockBean
    JwtTokenProvider tokenProvider;
	
	@MockBean 
	BookServiceImp bookServiceImpl;
	
	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	BookAPIController bookController;
	
	private List<Book> books = new ArrayList<>();
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	Book book1 =  new Book();
		book1.setAuthor("Williams Shakespear");
		book1.setBookId(1l);
		book1.setTitle("Hamlet");
		book1.setPublisher("Penguins");
		book1.setCountry("England");
		book1.setGenre(Genre.CLASSIC);
		book1.setIsbn("ISBN128283");
		
		books.add(book1);
		
	Book book2 =  new Book();
		book2.setAuthor("Williams Shakespear");
		book2.setBookId(1l);
		book2.setTitle("Hamlet");
		book2.setPublisher("Penguins");
		book2.setCountry("England");
		book2.setGenre(Genre.BIOGRAPHY);
		book2.setIsbn("ISBN128283");
		
		
		books.add(book2);
	}

	
	@Test
	@DisplayName("GET BOOK BY BOOK ID")
	void getBookByIdTest() throws Exception{
		// ARRANGE
		when(bookServiceImpl.getBookByBookId(anyLong())).thenReturn(books.get(0));
		
		// ACT
		mockMvc.perform(get("/api/v1/books/"+2l))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.title",equalTo("Hamlet")))
		.andExpect(jsonPath("$.author").value("Williams Shakespear"))
        .andDo(print());
	}
	
	@Test 
	@DisplayName("GET ALL BOOKS")
	void getAllBooksInStore() throws Exception{
		
		//ARRANGE
		when(bookServiceImpl.getAllBook()).thenReturn(books);
	
		mockMvc.perform(get("/api/v1/books"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$",hasSize(2)))
		.andExpect(jsonPath("$").isArray())
        .andDo(print());
	}
	
	@AfterEach
	void tearDown() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

}
