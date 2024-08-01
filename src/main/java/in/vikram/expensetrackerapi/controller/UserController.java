package in.vikram.expensetrackerapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.vikram.expensetrackerapi.Service.UserService;
import in.vikram.expensetrackerapi.entity.User;

@RestController
public class UserController {
	
	@Autowired
	UserService userService;
	
	
	
	
	@GetMapping("/user/{id}")
	public User getUsersDetailById(@PathVariable Long id) {
		return userService.getUserDetailsById(id);
	}
	
	@PutMapping("/user/{id}")
	public User updateUserDetails(@RequestBody User user,@PathVariable Long id) {
		return userService.updateUserInfo(user, id);
	}
	
	@DeleteMapping("/user/{id}")
	public String deleteUserInformation(@PathVariable Long id) {
		userService.deleteUserInfo(id);
		return "User is deleted successfully ";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
