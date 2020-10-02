package com.Tim401_6.Broker.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.management.relation.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.Tim401_6.Broker.model.User;
import com.Tim401_6.Broker.repository.UserRepository;

//This class takes a username and returns a user connected with that username. For now, it only 
//returns a dummmy user. This should be changed in the future so that the class
//connects to our database and reads the line where database.username = username
@Service
public class MyUserDetailsService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	//FOR SOME REASON userRepository return NULL
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
        

		System.out.println("We start here");
		User user;
		List<User> users = userRepository.findFirstByUsername(username);

	        if(users.size() > 0) {
	            user =  users.get(0);
	        } else {
	            return null;
	        }
		
	    System.out.println("Username: " + user.getUsername());
		System.out.println("Password: " + user.getPassword());
		System.out.println("Usertype: " + user.getType());


		
		ArrayList<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		SimpleGrantedAuthority a = new SimpleGrantedAuthority(user.getType());
		authorities.add(a);
		
	    return new org.springframework.security.core.userdetails.User(
	            user.getUsername(), user.getPassword(), true, true, true, 
	            true, authorities);  
		//return new org.springframework.security.core.userdetails.User("foo", "foo", new ArrayList<>()); //a hardcoded dummy user
	
	}

	//Since we are not really using separate privileges (for us a role is the same as a privilege)
	//we are creating an authority simply as a name for a role
    private Collection<? extends GrantedAuthority> getAuthorities(
    	      Collection<Role> roles) {
    	  
    	 		List<GrantedAuthority> authorities = new ArrayList<>();
    	 			for (Role r : roles) {
    	 					authorities.add(new SimpleGrantedAuthority(r.getRoleName()));
    	 				}
    	 		return authorities;
    	    }
    

}
