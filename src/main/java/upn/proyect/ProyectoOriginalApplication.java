package upn.proyect;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class ProyectoOriginalApplication  implements CommandLineRunner{
	
	@Autowired
	private BCryptPasswordEncoder encriptar;
	
	public static void main(String[] args) {
		SpringApplication.run(ProyectoOriginalApplication.class, args);
	}

	
	@Override
	public void run(String... args) throws Exception {
		String pass1 = "user";
		String pass2 = "admin";
		
		System.out.println(encriptar.encode(pass1));
		System.out.println(encriptar.encode(pass2));
	}
	
}
