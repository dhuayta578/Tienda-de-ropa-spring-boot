package upn.proyect;

import javax.servlet.http.HttpSessionIdListener;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.server.session.HeaderWebSessionIdResolver;

@Configuration
public class WebSecurityConfig<CookieSerializer> {
	
	@Autowired
	private DataSource dataSource;
	@Autowired
	private BCryptPasswordEncoder encriptar;
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	    http.authorizeRequests()
	        .antMatchers("/Inicio", "/Presentacion","/categoria/producto/{id}", "/categoria/producto/detalle/{id}", "/registro", "/registro/rol","/ventas/seleccion"
	        		,"/ventas/buscar","/ventas/{idUsuario}","/detalle/seleccion","/detalle/buscar","/detalle/{idProducto}","/pedidos")
	            .permitAll()
	        .antMatchers("/categoria","/categoria/producto/detalle/cesto/{id}", "/cesta", "/cesta/eliminar/{id}", "/cesta/comprar",
	                "/guardarVenta/**", "/compraExitosa")
	            .permitAll()
	        .antMatchers("/create", "/categoria/Admin","/categoria/producto/Admin/{id}","/guardar","/editar/{id}","/ventas").permitAll()
	        .antMatchers(HttpMethod.POST, "/login").permitAll()
	        .anyRequest().authenticated()
	        .and()
	        .formLogin()
	            .loginPage("/login")
	            .permitAll()
	        .and()
	        .logout().logoutUrl("/logout")
	        .logoutSuccessUrl("/login").permitAll();
	    http.headers()
        .contentSecurityPolicy("https://fonts.googleapis.com/css2?family=Poppins");
	    return http.build();
	}

	
	@Autowired
	public void configurerSecurityGlobal(AuthenticationManagerBuilder builder) throws Exception{
		builder.jdbcAuthentication()
		.dataSource(dataSource)
		.passwordEncoder(encriptar)
		.usersByUsernameQuery("Select usuario,password,estado from usuarios where usuario=?")
		.authoritiesByUsernameQuery("select u.usuario, r.rol from roles r inner join usuarios u on r.usuario_id=u.id where u.usuario=?");
	}
	
	@Bean
    public BCryptPasswordEncoder  passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(passwordEncoder());
    }
}
