package in.vikram.expensetrackerapi.entity;

import org.springframework.stereotype.Component;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Component
public class UserModel {

	
	@NotBlank(message = "Name should not be empty")
	private String name;
	
	@NotNull(message = "Email should not be null")
	@Email(message = "Enter a valid email")
	private String email;
	
	@NotNull(message = "password should not be null")
	@Size(min = 5, message = "passwrod should be atleast 5 character")
	private String password;
	
	private Long age = 0L;
	
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getAge() {
		return age;
	}

	public void setAge(Long age) {
		this.age = age;
	}

	public UserModel(String name, String email, String password, Long age) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.age = age;
	}

	public UserModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "UserModel [name=" + name + ", email=" + email + ", password=" + password + ", age=" + age + "]";
	}
	
	
}
