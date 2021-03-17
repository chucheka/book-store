package net.polarisdigitech.book_store.book_store.config;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.polarisdigitech.book_store.book_store.payload.ErrorResponse;

@Component
public class JWTAccessDeniedHandler implements AccessDeniedHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,AccessDeniedException accessDeniedException) throws IOException, ServletException {
	
		response.setStatus(HttpStatus.FORBIDDEN.value());
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		
		List<String> errors = Arrays.asList("Unauthorized Access Denied");
		
		ErrorResponse apiError = new ErrorResponse(HttpStatus.FORBIDDEN,accessDeniedException.getLocalizedMessage(),errors);
		
		OutputStream os = response.getOutputStream();
		
		ObjectMapper om = new ObjectMapper();
		
		om.writeValue(os, apiError);
		
		os.flush();
				

	}

}
