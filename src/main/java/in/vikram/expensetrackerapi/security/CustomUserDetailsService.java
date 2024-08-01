package in.vikram.expensetrackerapi.security;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import in.vikram.expensetrackerapi.entity.User;
import in.vikram.expensetrackerapi.repo.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		 User exsitingUser=userRepository.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException("User is not present for this email "+email));	
		
		 return new org.springframework.security.core.userdetails.User(exsitingUser.getEmail(), exsitingUser.getPassword(), new ArrayList<>());
	}

}
