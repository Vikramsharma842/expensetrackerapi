package in.vikram.expensetrackerapi.ServiceImpl;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import in.vikram.expensetrackerapi.Service.UserService;
import in.vikram.expensetrackerapi.entity.User;
import in.vikram.expensetrackerapi.entity.UserModel;
import in.vikram.expensetrackerapi.exception.ItemAlreadyExistException;
import in.vikram.expensetrackerapi.exception.ResourceNotFoundException;
import in.vikram.expensetrackerapi.repo.UserRepository;


@Service
public class UserServiceImpl implements UserService{

	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder bCryptEncoder;
	
	@Override
	public User createUser(UserModel user) {
		if(userRepository.existsByEmail(user.getEmail())) {
			throw new ItemAlreadyExistException("User is already register with email: "+user.getEmail());
		}
		User newUser = new User();
		 BeanUtils.copyProperties(user, newUser);
		 newUser.setPassword(bCryptEncoder.encode(newUser.getPassword()));
		 return userRepository.save(newUser);
	}
	@Override
	public User getUserDetailsById(Long id) {
		Optional<User> user =  userRepository.findById(id);
		if(user.isPresent()) {
			return user.get();
		}
		throw new ResourceNotFoundException("User with id " + id + " is not present");
	}
	
	// update user info
	@Override
	public User updateUserInfo(User user, Long id) throws ResourceNotFoundException {
		User oUser = getUserDetailsById(id);
		oUser.setName(user.getName()!= null ? user.getName() : oUser.getName());
		oUser.setEmail(user.getEmail()!= null ? user.getEmail() : oUser.getEmail());
		oUser.setPassword(user.getPassword()!= null ? bCryptEncoder.encode(user.getPassword()) : oUser.getPassword());
		oUser.setAge(user.getAge()!= null ? user.getAge() : oUser.getAge());
		
		return userRepository.save(oUser);
	}
	@Override
	public void deleteUserInfo(Long id) throws ResourceNotFoundException{
		User user = getUserDetailsById(id);
		userRepository.deleteById(user.getId());
	}

}
