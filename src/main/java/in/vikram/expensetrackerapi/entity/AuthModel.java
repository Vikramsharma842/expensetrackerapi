package in.vikram.expensetrackerapi.entity;

public class AuthModel {

	private String email;
	
	private String password;
	
	
	
	//getter and setter
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
	public AuthModel(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}
	public AuthModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "AuthModel [email=" + email + ", password=" + password + "]";
	}
	
	
}
