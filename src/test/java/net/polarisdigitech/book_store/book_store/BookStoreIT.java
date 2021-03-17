package net.polarisdigitech.book_store.book_store;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.polarisdigitech.book_store.book_store.config.JwtTokenProvider;
import net.polarisdigitech.book_store.book_store.controller.BookAPIController;
import net.polarisdigitech.book_store.book_store.dto.SignInDto;
import net.polarisdigitech.book_store.book_store.dto.SignUpDto;
import net.polarisdigitech.book_store.book_store.entity.Book;
import net.polarisdigitech.book_store.book_store.entity.Genre;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
class BookStoreIT {
	
	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	BookAPIController bookController;
	
	static final String email = "ryanucheka@gmail.com";
    static final String password = "chike22ucheka";
    static final String username = "Ucheka Chike";
    
    String token = null;
    
    SignInDto dto;
    
    SignUpDto dtoSU;
	
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
		book1.setGenre(Genre.DRAMA);
		book1.setIsbn("ISBN128283");
		
		books.add(book1);
		
	Book book2 =  new Book();
		book2.setAuthor("Williams Shakespear");
		book2.setBookId(1l);
		book2.setTitle("Hamlet");
		book2.setPublisher("Penguins");
		book2.setCountry("England");
		book2.setGenre(Genre.FICTION);
		book2.setIsbn("ISBN128283");
		
		
		books.add(book2);
	}

	@Test
	@DisplayName("USER SIGN UP")
	@Order(1)
	void signUpUpUser() throws Exception{
	
		dtoSU = new SignUpDto();
	    
	    dtoSU.setEmail(email);
	    dtoSU.setPassword(password);
	    dtoSU.setUsername(username);
		mockMvc.perform(post("/api/v1/auth/signup")
	    		.contentType(MediaType.APPLICATION_JSON_VALUE)
	            .content(asJsonString(dtoSU)))
	            .andExpect(status().isCreated())
	            .andDo(print());
		
	}
	
	@Test
	@DisplayName("USER SIGN IN")
	@Order(2)
	void signInUser() throws Exception{
		
		dto = new SignInDto(email,password);

		
		 MvcResult result = mockMvc.perform(post("/api/v1/auth/signin")
		    		.contentType(MediaType.APPLICATION_JSON_VALUE)
		            .content(asJsonString(dto)))
		            .andExpect(status().isOk())
		            .andReturn();

		    String response = result.getResponse().getContentAsString();
		    
		    ObjectMapper om = new ObjectMapper();
		    
		    JsonNode jsonNode = om.readTree(response);
		    
		    token = jsonNode.get("result").asText();
		   
	}
	
	@Test
	@DisplayName("GET BOOK BY BOOK ID")
	@Order(3)
	void getBookByIdTest() throws Exception{
	
		dto = new SignInDto(email,password);

		
		 MvcResult result = mockMvc.perform(post("/api/v1/auth/signin")
		    		.contentType(MediaType.APPLICATION_JSON_VALUE)
		            .content(asJsonString(dto)))
		            .andExpect(status().isOk())
		            .andReturn();

		    String response = result.getResponse().getContentAsString();
		    
		    ObjectMapper om = new ObjectMapper();
		    
		    JsonNode jsonNode = om.readTree(response);
		    
		    token = jsonNode.get("result").asText();

		
		mockMvc.perform(get("/api/v1/books/"+1l)
				.header("Authorization", "Bearer "+token))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.title",equalTo("Chike And The River")))
        .andDo(print());
	}
	
	@Test 
	@DisplayName("GET ALL BOOKS")
	@Order(4)
	void getAllBooksInStore() throws Exception{
		
		dto = new SignInDto(email,password);

		
		 MvcResult result = mockMvc.perform(post("/api/v1/auth/signin")
		    		.contentType(MediaType.APPLICATION_JSON_VALUE)
		            .content(asJsonString(dto)))
		            .andExpect(status().isOk())
		            .andReturn();

		    String response = result.getResponse().getContentAsString();
		    
		    ObjectMapper om = new ObjectMapper();
		    
		    JsonNode jsonNode = om.readTree(response);
		    
		    token = jsonNode.get("result").asText();

		
		mockMvc.perform(get("/api/v1/books")
				.header("Authorization", "Bearer "+token))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$",hasSize(2)))
		.andExpect(jsonPath("$").isArray())
        .andDo(print());
	}
	
	@Test
	@DisplayName("POST BOOK TEST")
	@Order(5)
	void createBookTest() throws Exception{
		dto = new SignInDto(email,password);

		
		 MvcResult result = mockMvc.perform(post("/api/v1/auth/signin")
		    		.contentType(MediaType.APPLICATION_JSON_VALUE)
		            .content(asJsonString(dto)))
		            .andExpect(status().isOk())
		            .andReturn();

		    String response = result.getResponse().getContentAsString();
		    
		    ObjectMapper om = new ObjectMapper();
		    
		    JsonNode jsonNode = om.readTree(response);
		    
		    token = jsonNode.get("result").asText();

		mockMvc.perform(post("/api/v1/books")
				.header("Authorization", "Bearer "+token)
				.contentType(MediaType.APPLICATION_JSON)
		        .content(asJsonString(books.get(0))) 
		        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andDo(print());
	}
	
	 static String asJsonString(final Object obj) {
	        try {
	            return new ObjectMapper().writeValueAsString(obj);
	        } catch (Exception e) {
	            throw new RuntimeException(e);
	        }
	    }
	
	@AfterEach
	void tearDown() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

}
