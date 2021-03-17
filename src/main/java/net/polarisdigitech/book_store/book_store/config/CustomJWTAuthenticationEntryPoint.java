package net.polarisdigitech.book_store.book_store.config;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.polarisdigitech.book_store.book_store.payload.ErrorResponse;

@Component
public class CustomJWTAuthenticationEntryPoint extends Http403ForbiddenEntryPoint{

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException ex)
			throws IOException {
		
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		
		List<String> errors = new ArrayList<>();
		
		errors.add("Authentication Failed");
		
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.UNAUTHORIZED,ex.getLocalizedMessage(),errors);
		
		OutputStream os = response.getOutputStream();
		
		ObjectMapper om  = new ObjectMapper();
		
		om.writeValue(os, errorResponse);
		
		os.flush();
		
	}

	
}
