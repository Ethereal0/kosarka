package com.kosarka.service;

import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.kosarka.model.User;
import com.kosarka.model.dto.NewUserDTO;
import com.kosarka.repository.UserRepository;

@Transactional
@Service
public class UserService implements UserDetailsService {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

	private final UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{
		try {
			User user = userRepository.findByUsername(username);
			if(user == null){
				LOGGER.debug("User not found");
			}
			LOGGER.debug("User:" + user.toString());
			return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthorities(user));
		}catch (Exception e){  
            throw new UsernameNotFoundException("User not found");  
        }  
	}
	
    private Set<GrantedAuthority> getAuthorities(User user){  
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();  
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(user.getRole());  
        authorities.add(grantedAuthority);  
        
        LOGGER.debug("user authorities are " + authorities.toString());  
        return authorities;  
    }  
    
	public User getById(Integer User_id) {
		User user = userRepository.findOne(User_id);
		return user;
	}
	
	public User getByUsername(String username) {
		User user = userRepository.findByUsername(username);
		return user;
	}

	public void createUser(NewUserDTO newUserDTO) {
		User user = new User(newUserDTO.getUsername(), newUserDTO.getPassword(), "ROLE_ADMIN");
		userRepository.save(user);
	}

}
