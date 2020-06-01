package com.modestack.ahmed.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.modestack.ahmed.models.UserDto;
import com.modestack.ahmed.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	public UserDto saveUser(UserDto user) {
		UserDto response = userRepository.save(user);
		return response;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		UserDto userdetails = userRepository.fetchUserDetailsByUserName(username);

		return new User(userdetails.getUserName(), userdetails.getPassword(), new ArrayList<>());
	}
}
