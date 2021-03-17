package net.polarisdigitech.book_store.book_store.services;



import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import net.polarisdigitech.book_store.book_store.entity.User;
import net.polarisdigitech.book_store.book_store.entity.UserPrincipal;
import net.polarisdigitech.book_store.book_store.repository.UserRepository;


@Service
public class CustomUserDetailsService implements UserDetailsService {
    
    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        
    	User user = userRepository.findByEmail(email)
    			.orElseThrow(()-> new UsernameNotFoundException("User not found with email: " + email));
        return new UserPrincipal(user);
        
    }

    // This method is used by JWTAuthenticationFilter
    @Transactional
    public UserDetails loadUserById(Long id) {
    	
        User user = userRepository.findById(id).orElseThrow(
            () -> new UsernameNotFoundException("User not found with id : " + id)
        );

        return new UserPrincipal(user);
    }
    
}