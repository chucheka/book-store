package net.polarisdigitech.book_store.book_store.services;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import net.polarisdigitech.book_store.book_store.config.JwtTokenProvider;
import net.polarisdigitech.book_store.book_store.dto.SignInDto;
import net.polarisdigitech.book_store.book_store.dto.SignUpDto;
import net.polarisdigitech.book_store.book_store.entity.Role;
import net.polarisdigitech.book_store.book_store.entity.RoleName;
import net.polarisdigitech.book_store.book_store.entity.User;
import net.polarisdigitech.book_store.book_store.exception.AppException;
import net.polarisdigitech.book_store.book_store.exception.AuthenticationFailedException;
import net.polarisdigitech.book_store.book_store.exception.BadRequestException;
import net.polarisdigitech.book_store.book_store.payload.ApiResponse;
import net.polarisdigitech.book_store.book_store.repository.RoleRepository;
import net.polarisdigitech.book_store.book_store.repository.UserRepository;





@Service
public class AuthService {
	
	@Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    PasswordEncoder passwordEncoder;
    
    @Autowired
    JwtTokenProvider tokenProvider;
    
    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);
    
    private static final long EXPIRE_TOKEN_AFTER_MINUTES = 30;
    
    public ApiResponse authenticate(SignInDto dto){
    
//       try {
    	   Authentication authentication = authenticationManager.authenticate(
                   new UsernamePasswordAuthenticationToken(
                           dto.getEmail(),
                           dto.getPassword()
                   )
           );

           SecurityContextHolder.getContext().setAuthentication(authentication);

           String jwt = tokenProvider.generateToken(authentication);
           ApiResponse res = new ApiResponse(HttpStatus.OK.name(),jwt);
           
           return res;
       
       

    }
    
    public ApiResponse registerUser(SignUpDto dto) throws BadRequestException{

        
        if(userRepository.existsByEmail(dto.getEmail())) {
       	 throw new BadRequestException("There is an account with this email : " + dto.getEmail());
       }
        
        Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new AppException("User Role not set."));
         
        User user = new User.UserBuilder(dto.getUsername(),dto.getEmail(),Collections.singleton(userRole))
        		.password(passwordEncoder.encode(dto.getPassword()))
        		.build();
    
      
        
        User savedUser = userRepository.save(user);
        
        		 
        return new ApiResponse(HttpStatus.CREATED.name(),savedUser);
    }

}
