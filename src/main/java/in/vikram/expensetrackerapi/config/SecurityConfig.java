package in.vikram.expensetrackerapi.config;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

//	@Autowired
//	DataSource dataSource;
	
//	@Autowired
//	CustomUserDetailsService customUserDetailsService;
//	
	// Step 1
	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests((requests) -> requests
				.requestMatchers("/auth/**").permitAll()
		.anyRequest().authenticated())
		.csrf(csrf -> csrf.disable());
		http.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		//http.formLogin(withDefaults());
		http.httpBasic(withDefaults());
		return http.build();
	}
	
//	@Bean
//	public CustomUserDetailsService customUserDetailsService() {
//		return new CustomUserDetailsService();
//	}
	
	//Step 2
	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails user1 = User.withUsername("user1")
				.password("{noop}userPass")
				.roles("USER")
				.build();
		UserDetails admin1 = User.withUsername("admin1")
				.password("{noop}adminPass")
				.roles("ADMIN")
				.build();
		
		return new InMemoryUserDetailsManager(user1,admin1);
	}
////		JdbcUserDetailsManager userDetailsManager = 
////				new JdbcUserDetailsManager(dataSource);
////		userDetailsManager.createUser(user1);
////		userDetailsManager.createUser(admin);
////		return userDetailsManager;
//		
//	}
//	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
//	@Bean
//	public AuthenticationManager authenticationManagerBean() throws Exception {
//		return authenticationManagerBean();
//	}
	
	
	
	
}
