package upn.proyect.controlador;



import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import upn.proyect.entidades.Categoria;
import upn.proyect.entidades.Rol;
import upn.proyect.entidades.Usuario;
import upn.proyect.repositorio.IRolRepo;
import upn.proyect.repositorio.IUsuarioRepo;
import upn.proyect.servicio.UsuarioServicio;
import org.springframework.security.web.csrf.CsrfToken;
@Controller
public class userControlador {
	
	@Autowired
    private IUsuarioRepo usuarioRepo;
	@Autowired
    private UsuarioServicio usuarioServi;
	@Autowired
    private IRolRepo rolRepo;
	@Autowired
	private BCryptPasswordEncoder encriptar;
	

	@GetMapping("/login")
	public String login() {
	    return "login";
	}
	
	@PostMapping("/login")
	public String login(@RequestParam(name = "username") String username,
	                           @RequestParam(name = "password") String password,
	                           HttpServletResponse response) {
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	    String encryptedPassword = encoder.encode(password);

	    // Save the username and encrypted password in a cookie
	    Cookie cookie = new Cookie("usuario", encryptedPassword);
	    response.addCookie(cookie);

	    return "redirect:/2141242342";

	    /*BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	    String encryptedPassword = encoder.encode(password);

	    // Save the username and encrypted password in a cookie
	    Cookie cookie = new Cookie("usuario", encryptedPassword);
	    response.addCookie(cookie);

	    return "redirect:/Inicio";*/
	}
	
	/*@GetMapping("/login")
	public String login() {
		return "login";
	}*/
	
	
	
	@GetMapping("/registro")
    public String registro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "registro";
    }
	
	@PostMapping("/registro")
	public String guardar(@RequestParam(name="nombre" ,required=true, defaultValue="") String nombre,
			@RequestParam(name="apellido" ,required=true, defaultValue="") String apellido,
			@RequestParam(name="usuario" ,required=true, defaultValue="") String usuario,
			@RequestParam(name="password" ,required=true, defaultValue="") String password,
			@RequestParam(name="estado" ,required=true, defaultValue="1") String estado,
			Model model) {
		System.out.println("Guardado");
		
		Usuario usuario1 = new Usuario();
		usuario1.setNombre(nombre);
		usuario1.setApellido(apellido);
		usuario1.setUsuario(usuario);
		usuario1.setPassword(encriptar.encode(password));
		usuario1.setEstado(estado);
		usuario1 = usuarioRepo.save(usuario1);
		
		return "redirect:/registro/rol";
	}
	
	@GetMapping("/registro/rol")
    public String registro2(Model model) {
        model.addAttribute("rol", new Rol());
        
	    List<Usuario> usuarios = usuarioServi.listarU();
		model.addAttribute("usuarios", usuarios);
		
        return "registroRol";
    }
	
	@PostMapping("/registro/rol")
	public String guardar2(@RequestParam(name="usuario_id", required=true) int usuario_id,
	        @RequestParam(name="rol" ,required=true, defaultValue="") String rol,
	        Model model) {
	    System.out.println("Guardado");

	    Usuario usuario = usuarioServi.buscarPorID(usuario_id);
	    if(usuario == null) {
	        // Manejo del error en caso de que el usuario no exista
	        return "redirect:/registro";
	    }

	    Rol rol1 = new Rol();
	    rol1.setUsuario_id(usuario);
	    rol1.setRol(rol);
	    rol1 = rolRepo.save(rol1);
	   

	    return "redirect:/login";
	}
	
	
    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){    
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login";//Redirigir al login con un parametro indicando que se cerró la sesión
    }

}
