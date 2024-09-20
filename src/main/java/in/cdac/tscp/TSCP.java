package in.cdac.tscp;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

//import in.cadc.tscp.controllers.UserController;
//import in.cdac.tscp.security.AppProperties;
//import in.cdac.tscp.security.WebSecurity;
//import in.cdac.tscp.service.UserService;

@SpringBootApplication
//@ComponentScan(basePackageClasses = {UserController.class, UserService.class, WebSecurity.class, AppProperties.class })
@ComponentScan({"in.cadc.tscp.controllers", "in.cdac.tscp.service", "in.cdac.tscp.security", "in.cdac.tscp.exceptions","in.cdac.tscp"})
public class TSCP {

	public static void main(String[] args) {
		System.setProperty("spring.devtools.restart.enabled", "false");
		SpringApplication.run(TSCP.class, args);
	}

	@Bean
	public BCryptPasswordEncoder bcryptPasswordEncoder() 
	{
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SpringApplicationContext springApplicationContext() 
	{
		return new SpringApplicationContext();
	}

//	@Bean
//	CorsConfigurationSource corsConfigurationSource() {
//		CorsConfiguration configuration = new CorsConfiguration();
//		configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
//		configuration.setAllowedMethods(Arrays.asList("GET","POST"));
//		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//		source.registerCorsConfiguration("/**", configuration);
//		return source;
//	}

}
