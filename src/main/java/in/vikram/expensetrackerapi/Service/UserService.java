package in.vikram.expensetrackerapi.Service;

import in.vikram.expensetrackerapi.entity.User;
import in.vikram.expensetrackerapi.entity.UserModel;

public interface UserService {

	
	User createUser(UserModel user);
	
	User getUserDetailsById(Long id);
	
	User updateUserInfo(User user , Long id);
	
	public void deleteUserInfo(Long id);
}
