/*package com.serverapp.api.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.serverapp.api.entites.User;
import com.serverapp.api.repos.UserRepository;
import com.serverapp.api.security.JwtUserDetails;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepository;
	
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByuserName(username);
		return JwtUserDetails.create(user);
	}
	
	public UserDetails loadUserById(Integer id) {
		User user = userRepository.findById(id).get();
		return JwtUserDetails.create(user); 
	}
    
}*/
