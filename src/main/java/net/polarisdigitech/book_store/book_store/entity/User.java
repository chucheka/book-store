package net.polarisdigitech.book_store.book_store.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class User 
{
    //All  attributes
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private  Long userId;
	
    private String username; // required
    private String email; // required
    private String password; // optional
 
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();
    
    
    
    private User() {
		super();
	}

	private User(UserBuilder builder) {
        this.userId = builder.userId;
		this.username = builder.username;
        this.email = builder.email;
        this.password = builder.password;
        this.roles = builder.roles;
    }
 
    //All getter, and NO setter to provde immutability
    public String getUsername() {
        return username;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
    public Long getUserId() {
        return userId;
    }
    
    
    
	public Set<Role> getRoles() {
		return roles;
	}

	@Override
    public String toString() {
        return "User: "+this.username+", "+this.email+", "+this.password+", "+this.userId+" ";
    }
 
    public static class UserBuilder 
    {
        private Long userId;
        private  String username;
        private  String email;
        private String password;
        private Set<Role> roles = new HashSet<>();
       
        
        public UserBuilder(String username, String email,Set<Role> roles) {
            this.username = username;
            this.email = email;
            this.roles = roles;
        }
        
        public UserBuilder password(String password) {
            this.password = password;
            return this;
        }
        
        public UserBuilder userId(Long userId) {
            this.userId = userId;
            return this;
        }
       
        public User build() {
            User user =  new User(this);
            validateUserObject(user);
            return user;
        }
        private void validateUserObject(User user) {
            //Do some basic validations to check 
            //if user object does not break any assumption of system
        }
    }
}