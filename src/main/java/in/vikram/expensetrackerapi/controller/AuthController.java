package in.vikram.expensetrackerapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import in.vikram.expensetrackerapi.Service.UserService;
import in.vikram.expensetrackerapi.entity.User;
import in.vikram.expensetrackerapi.entity.UserModel;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	UserService userService;
	
	
	
	@PostMapping("/register")
	@ResponseStatus(value = HttpStatus.CREATED)
	public User createNewUser(@Valid @RequestBody UserModel user) {
		
		return userService.createUser(user);
	}
	
//	@PostMapping("/login")
//	public ResponseEntity<HttpStatus>login(@RequestBody AuthModel authModel){
//		Authentication authentication= authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authModel.getEmail(), authModel.getPassword()));
//		
//		SecurityContextHolder.getContext().setAuthentication(authentication);
//		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
//	}
	@PostMapping("/login")
	public ResponseEntity<String>login(){
		
		return new ResponseEntity<String>("User is logged int" , HttpStatus.OK);
	}
	
	
//	@PreAuthorize("hasRole('USER')")
//	@GetMapping("/user")
//	public ResponseEntity<String> login(){
//		return new ResponseEntity<String>("User is logged in ",HttpStatus.OK);
//	}
//	
//	@PreAuthorize("hasRole('ADMIN')")
//	@GetMapping("/admin")
//	public ResponseEntity<String> admin(){
//		return new ResponseEntity<String>("User is logged in as admin ",HttpStatus.OK);
//	}
	

	
}
