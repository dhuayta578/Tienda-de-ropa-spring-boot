 package upn.proyect.controlador;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import upn.proyect.IServicio.ICarritoServicio;
import upn.proyect.IServicio.ICategoriaServicio;
import upn.proyect.IServicio.IUsuarioServicio;
import upn.proyect.entidades.Carrito;
import upn.proyect.entidades.Categoria;
import upn.proyect.entidades.Detalle;
import upn.proyect.entidades.DetallePedidoC;
import upn.proyect.entidades.Producto;
import upn.proyect.entidades.Usuario;
import upn.proyect.entidades.Ventas;
import upn.proyect.repositorio.ICarritoRepo;
import upn.proyect.repositorio.ICategoriaRepo;
import upn.proyect.repositorio.IDetallePedidoRepo;
import upn.proyect.repositorio.IDetalleRepo;
import upn.proyect.repositorio.IProductoRepo;
import upn.proyect.repositorio.IUsuarioRepo;
import upn.proyect.repositorio.IVentasRepo;
import upn.proyect.servicio.CarritoServicio;
import upn.proyect.servicio.CategoriaServicio;
import upn.proyect.servicio.DetallePedidoCServicio;
import upn.proyect.servicio.DetalleServicio;
import upn.proyect.servicio.ProductoServicio;
import upn.proyect.servicio.UsuarioServicio;
import upn.proyect.servicio.VentasServicio;

//import java.util.logging.Logger;
//import java.util.logging.Level;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class controlador {
	
	
	@Autowired
	private CategoriaServicio categoriaserv;
	@Autowired
	private IProductoRepo produrepo;
	@Autowired
	private ProductoServicio produServicio;
	@Autowired
	private ICategoriaRepo caterepo;
	@Autowired
	private IUsuarioRepo usuarioRepo;
	@Autowired
	private VentasServicio ventaServicio;
	@Autowired
	private IVentasRepo ventaRepo;
	@Autowired
	private IDetalleRepo detalleRepo;
	@Autowired
	private VentasServicio ventaService;
	@Autowired
	private UsuarioServicio usuarioService;
	@Autowired
	private DetalleServicio detalleServicio;
	@Autowired
	private DetallePedidoCServicio detallePedidoServicio;
	@Autowired
	private IDetallePedidoRepo detallePedidoRepo;
	
	 @GetMapping("/Inicio")
	    public String index() {
	        return "index";
	    }
	 
	 @GetMapping("/Presentacion")
	    public String Presentacion() {
	        return "Presentacion";
	    }
	 
		
		@GetMapping("/pedidos")
		public String listaPedidos(Model modelo,Authentication authentication) {	    
			try {
		        boolean isUser = authentication.getAuthorities().stream()
		                .anyMatch(authority -> authority.getAuthority().equals("ROLE_USER"));

		        if (!isUser) {
		            modelo.addAttribute("error", "No eres usuario");
		            System.out.println("No eres usuario");
		            return "error1";
		        }
	        
	       
	        
	        List<DetallePedidoC> pedidos = detallePedidoRepo.findAll();
	        modelo.addAttribute("pedidos", pedidos);
	        return "ListaPedidos";
			} catch (Exception e) {
		        modelo.addAttribute("error", "No eres usuario");
		        System.out.println("No eres usuario");
		        return "error1";
		    }
		}
	 
	@GetMapping("/categoria") 
	public String listarC(Model modelo,Authentication authentication) {
		try {
	        boolean isUser = authentication.getAuthorities().stream()
	                .anyMatch(authority -> authority.getAuthority().equals("ROLE_USER"));

	        if (!isUser) {
	            modelo.addAttribute("error", "No eres usuario");
	            System.out.println("No eres usuario");
	            return "error1";
	        }

	        List<Categoria> categorias = categoriaserv.listar();
			modelo.addAttribute("categorias", categorias);
			return "FormCategoria";
	    } catch (Exception e) {
	        modelo.addAttribute("error", "No eres usuario");
	        System.out.println("No eres usuario");
	        return "error1";
	    }
		
	}
	

	
	@GetMapping("/categoria/producto/{id}")
    public String productosPorCategoria(@PathVariable int id, Model modelo) {	    
		    Categoria categoria = categoriaserv.buscarCategoria(id);
		    List<Producto> productos = categoriaserv.ProductoPorCategoria(categoria);
		    modelo.addAttribute("productos", productos);
		    return "FormProductos";
    }
	

	
	/*@GetMapping("/categoria/producto/detalle/{id}")
	public String detalle(@PathVariable int id,Model modelo) {
		Producto producto = categoriaserv.buscarPorID(id);
		modelo.addAttribute("productos",producto);	
		return "FormDetalle";
	}*/
	
	@GetMapping("/categoria/producto/detalle/{id}")
	public String detalle(@PathVariable String id, Model modelo) {
	    int productoId;
	    try {
	        productoId = Integer.parseInt(id);
	    } catch (NumberFormatException e) {
	        // El valor de ID no es un número válido
	        // Manejar el error adecuadamente, como mostrar un mensaje de error al usuario o redirigir a una página de error
	        return "error1";
	    }
	    
	    Producto producto = categoriaserv.buscarPorID(productoId);
	    if (producto == null) {
	        // El producto no existe o no se encontró
	        // Manejar el error adecuadamente, como mostrar un mensaje de error al usuario o redirigir a una página de error
	        return "error1";
	    }
	    
	    modelo.addAttribute("productos", producto);	
	    return "FormDetalle";
	}
	
	
	@GetMapping("/categoria/producto/detalle/cesto/{id}")
	public String añadirAlCarrito(@PathVariable int id, HttpSession session,Model modelo,@RequestParam("cantidadNumero") int cantidad) {
		Producto producto = categoriaserv.buscarPorID(id);
		modelo.addAttribute("productos",producto);
		
		Optional<Producto> productoOptional = produrepo.findById(id);
	    if (!productoOptional.isPresent()) {
	        modelo.addAttribute("error", "Producto no encontrado");
	        System.out.println("Producto no encontrado");
	        return "error1";
	    }

	    Producto productoEncontrado = productoOptional.get();
	    if (productoEncontrado.getStock() <= cantidad) {
	        modelo.addAttribute("error", "No hay suficiente stock");
	        System.out.println("No hay suficiente stock");
	        return "error1";
	    }

	    Carrito carrito = new Carrito();
	    carrito.setIdproducto(productoEncontrado);
	    carrito.setCantidad(cantidad);
	    
	    //Esto hara que se verifique la lista de carrito si existe, si no lo creara.
	    List<Carrito> carritos = (List<Carrito>) session.getAttribute("carritos");
	    if (carritos == null) {
	        carritos = new ArrayList<>();
	    }
	    
	    //En este se aumentara la cantidad del impuesta del producto, lo ponemos como false por defecto pero si se cumple la condicion dada
	    //que es si se encuentra otro producto con el mismo id se establecera como true y se agregara a la lista del carrito
	    boolean productoEncontradoEnCarrito = false;
	    for (Carrito carrito1 : carritos) {
	        if (carrito1.getIdproducto().getId() == productoEncontrado.getId()) {
	        	carrito1.setCantidad(carrito1.getCantidad() + cantidad);
	            productoEncontradoEnCarrito = true;
	            break;
	        }
	    }
	    //Aqui lo que se hara es verificar si el producto ya esta agregado al carrito del metodo anterior, si es asi entonces se actualizara la cantidad del producto
	    //del carrito y si no se cumple se crea en carrito un objeto con los datos producto y cantidad y se agregara a la lista
	    if (!productoEncontradoEnCarrito) {
	        Carrito carrito1 = new Carrito();
	        carrito1.setIdproducto(productoEncontrado);
	        carrito1.setCantidad(cantidad);
	        carritos.add(carrito);
	    }
	    
	    session.setAttribute("carritos", carritos);
	    
	    //Este funcion hara disminuir el Stock
	    productoEncontrado.setStock(productoEncontrado.getStock() - cantidad);
	    produrepo.save(productoEncontrado);

	    return "redirect:/cesta";
	}
	
	
	

	@GetMapping("/cesta")
	public String verCesta(HttpSession session, Model modelo) {
		
		//Aqui obtendremos la lista del objeto Carrito almacenado en la sesion, 
		//si en todo caso carritos es nula se agregara un mensaje en la consola de no hay productos
		List<Carrito> carritos = (List<Carrito>) session.getAttribute("carritos");
		if (carritos == null || carritos.isEmpty()) {
			modelo.addAttribute("error", "No hay productos en el carrito");
			System.out.println("No hay productos en el carrito");
			return "error1";
		}
		
		//Aqui lo que haremos es declarar un total de tipo double que lo hara sera almacenar el precio de todos los productos del carrito
		//recuperara por idproducto al producto y multiplicara su precio_unidad por la cantidad del carrito
		//para luego almacenar en total que al principio tendra un valor por defecto de 0.0 esta sera acomulativo
		Double total = 0.0;
		for (Carrito carrito : carritos) {
		    Optional<Producto> productoOptional = produrepo.findById(carrito.getIdproducto().getId());
		    if (!productoOptional.isPresent()) {
		        continue;
		    }
		    Producto producto = productoOptional.get();
		    total += producto.getPrecio_unidad() * carrito.getCantidad();
		}

		modelo.addAttribute("carritos", carritos);
		modelo.addAttribute("total", total);
		return "FormCesto";
	}
	
	@GetMapping("/cesta/eliminar/{id}")
	public String eliminarDelCarrito(@PathVariable int id, HttpSession session) {
	    List<Carrito> carritos = (List<Carrito>) session.getAttribute("carritos");
	    //Esto lo que hara sera eliminar por id del producto en el carrito, si carritos no es nulo entonces se buscara el idProducto
	    //una vez encontrado el id del producto en la base de datos con isPresent() lo que hara sera eliminar de la lista y se actualizara en la sesion.
	    if (carritos != null) {
	        Optional<Carrito> carritoOptional = carritos.stream().filter(c -> c.getIdproducto().getId() == id).findFirst();
	        if (carritoOptional.isPresent()) {
	            Carrito carritoEncontrado = carritoOptional.get();
	            carritos.remove(carritoEncontrado);
	            session.setAttribute("carritos", carritos);
	        }
	    }

	    return "redirect:/cesta";
	}
	
	@GetMapping("/cesta/comprar")
	public String realizarCompra(HttpSession session,Authentication authentication,Model modelo) {
	    String nombreUsuario = authentication.getName();
	    Usuario usuarioid = usuarioRepo.findByUsuario(nombreUsuario);
	    
	    UserDetails userDetails = (UserDetails) authentication.getPrincipal();
	    Usuario usuario = usuarioRepo.findByUsuario(userDetails.getUsername());
	    
	    List<Carrito> carritos = (List<Carrito>) session.getAttribute("carritos");
	    if (carritos == null || carritos.isEmpty()) {
	        modelo.addAttribute("error", "No hay productos en el carrito");
	        System.out.println("No hay productos en el carrito");
	        return "error1";
	    }
	    Double total = 0.0;
	    for (Carrito carrito : carritos) {
	        Optional<Producto> productoOptional = produrepo.findById(carrito.getIdproducto().getId());
	        if (!productoOptional.isPresent()) {
	            continue;
	        }
	        Producto producto = productoOptional.get();
	        total += producto.getPrecio_unidad() * carrito.getCantidad();
	    }
	    
	    //Ventas venta = new Ventas();
	    Ventas venta = new Ventas(); //0, usuario, LocalDate.now(), BigDecimal.valueOf(total));
	    venta.setId(0);
	    venta.setUsuario(usuarioid);
	    venta.setFechaVenta(LocalDate.now());
	    venta.setMontoTotal(BigDecimal.valueOf(total));
	    
	    modelo.addAttribute("total", total);
	    modelo.addAttribute("usuario", usuarioid);
	    modelo.addAttribute("carritos", carritos);
	    modelo.addAttribute("venta", venta);
	    
	    
	    for (Carrito carrito : carritos) {
	    	Optional<Producto> productoOptional = produrepo.findById(carrito.getIdproducto().getId());
	        if (!productoOptional.isPresent()) {
	            continue;
	        }
	        Producto producto = productoOptional.get();
	        
	        
	        Detalle detalle = new Detalle();
	        detalle.setId(0);
	        detalle.setProducto(producto);
	        detalle.setPrecio_unidad(producto.getPrecio_unidad());
	        detalle.setCantidad(carrito.getCantidad());
	        detalle.setEstado('1');
	        detalle = detalleRepo.save(detalle);
	        
	        DetallePedidoC detallePedido = new DetallePedidoC();
	        detallePedido.setId(0);
	        detallePedido.setUsuario_id(usuario); // Asigna el usuario correspondiente
	        detallePedido.setUsuario(usuario.getUsuario()); // Suponiendo que haya un campo 'nombre' en la entidad Usuario
	        detallePedido.setProducto_id(producto);
	        detallePedido.setProducto(producto.getDescripcion()); // Suponiendo que haya un campo 'nombre' en la entidad Producto
	        detallePedido.setPrecio_unidad(producto.getPrecio_unidad());
	        detallePedido.setCantidad(carrito.getCantidad());
	        detallePedido.setEstado("En proceso");

	        // No necesitas asignar detallePedidoRepo.save(detallePedido) a la misma variable
	        detallePedidoRepo.save(detallePedido);
	    }
	    
	    return "compra";
	}
	
	@GetMapping("/guardarVenta/{usuario}/{fechaVenta}/{total}")
	public String guardarVenta(@PathVariable("usuario") String nombreUsuario1,
	                            @PathVariable("fechaVenta") String fechaVenta,
	                            @PathVariable("total") double total,
	                            HttpSession session,
	                            Authentication authentication,
	                            Model modelo) {
		
		List<Carrito> carritos = (List<Carrito>) session.getAttribute("carritos");
		
		String nombreUsuario = authentication.getName();
	    Usuario usuario = usuarioRepo.findByUsuario(nombreUsuario);
	    
	    Ventas venta = new Ventas(); 
	    venta.setUsuario(usuario);
	    venta.setNombreUsuario(nombreUsuario1);
	    venta.setFechaVenta(LocalDate.parse(fechaVenta));
	    venta.setMontoTotal(BigDecimal.valueOf(total));
	    
	    venta = ventaRepo.save(venta);
	    modelo.addAttribute("mensaje", "Compra exitosa");
	    
	  
	    
	    session.removeAttribute("carrito");
	    return "compraExitosa";
	}
	
	@GetMapping("/ventas/seleccion")
	public String SeleccionID(Model model) {
		List<Integer> idsUsuarios = usuarioService.obtenerListaIdsUsuarios();
	    model.addAttribute("idsUsuarios", idsUsuarios);
	    model.addAttribute("usuario", new Usuario());
	    return "SeleccionID";
	}

	@PostMapping("/ventas/buscar")
	public String buscarUsuario(@ModelAttribute("usuario") Usuario usuario, Model model) {
	    int idUsuario = usuario.getId();
	    Usuario usuarioEncontrado = usuarioService.obtenerUsuarioPorId(idUsuario);
	    if(usuarioEncontrado == null) {
	        model.addAttribute("error", "No se encontró al usuario con ID " + idUsuario);
	        System.out.println("No se encontró al usuario con ID " + idUsuario);
	        return "error1";
	    }
	    return "redirect:/ventas/" + idUsuario;
	}

	@GetMapping("/ventas/{idUsuario}")
	public String verVentasPorUsuario(@PathVariable int idUsuario, Model model) {
	    List<Ventas> ventas = ventaService.obtenerVentasPorUsuario(idUsuario);
	    Usuario usuario = usuarioService.obtenerUsuarioPorId(idUsuario);
	    model.addAttribute("ventas", ventas);
	    model.addAttribute("usuario", usuario);
	    return "verVentasPorID";
	}
	
	//Detalle 
	
	@GetMapping("/detalle/seleccion")
	public String seleccionIDProducto(Model model) {
		List<Integer> idsProductos = produServicio.obtenerListaIdProducto();
	    model.addAttribute("idsProductos", idsProductos);
	    model.addAttribute("producto", new Producto());
	    return "SeleccionIDProducto";
	}

	@PostMapping("/detalle/buscar")
	public String buscarIDProducto(@ModelAttribute("producto") Producto producto, Model model) {
	    int idProducto = producto.getId();
	    Producto productoEncontrado = produServicio.obtenerProductoPorId(idProducto);
	    if(productoEncontrado == null) {
	        model.addAttribute("error", "No se encontró al producto con ID " + idProducto);
	        System.out.println("No se encontró al producto con ID " + idProducto);
	        return "error1";
	    }
	    return "redirect:/detalle/" + idProducto;
	}

	@GetMapping("/detalle/{idProducto}")
	public String verDetalleProducto(@PathVariable int idProducto, Model model) {
	    List<Detalle> detalles = detalleServicio.obtenerDetallePorProducto(idProducto);
	    Producto producto = produServicio.obtenerProductoPorId(idProducto);
	    model.addAttribute("detalles", detalles);
	    model.addAttribute("producto", producto);
	    return "verDetallePorID";
	}
	
		
	//ADMINISTRADOR//
	@GetMapping("/categoria/Admin") 
	public String listarCA(Model modelo,Authentication authentication) {
		try {
	        boolean isAdmin = authentication.getAuthorities().stream()
	                .anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"));

	        if (!isAdmin) {
	            modelo.addAttribute("error", "No eres administrador");
	            System.out.println("No eres administrador");
	            return "error1";
	        }

	        List<Categoria> categorias = categoriaserv.listar();
	        modelo.addAttribute("categorias", categorias);
	        return "FormCategoriaAdmin";
	    } catch (Exception e) {
	        modelo.addAttribute("error", "No eres administrador");
	        System.out.println("No eres administrador");
	        return "error1";
	    }
	}
	

	  
		@GetMapping("/categoria/producto/Admin/{id}")
	    public String productosPorCategoriaA(@PathVariable int id, Model modelo) {	    
			    Categoria categoria = categoriaserv.buscarCategoria(id);
			    List<Producto> productos = categoriaserv.ProductoPorCategoria(categoria);
			    modelo.addAttribute("productos", productos);
			    return "FormProductosAdmin";
	    }
		
		@GetMapping("/create")
		public String create(Model modelo,Authentication authentication) {
		    try {
		        boolean isAdmin = authentication.getAuthorities().stream()
		                .anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"));

		        if (!isAdmin) {
		            modelo.addAttribute("error", "No eres administrador");
		            System.out.println("No eres administrador");
		            return "error1";
		        }
		        modelo.addAttribute("productos", new Producto());
			    modelo.addAttribute("categorias", categoriaserv.listar()); // Agregar la lista de categorías al modelo
			    return "create";
			    
		    } catch (Exception e) {
		        modelo.addAttribute("error", "No eres administrador");
		        System.out.println("No eres administrador");
		        return "error1";
		    }
		}
		
		 @PostMapping("/salvar")
		    public String salvarRopa(@ModelAttribute Producto producto) {
		        // Aquí puedes realizar la lógica para guardar la nueva ropa en la base de datos
			 	produrepo.save(producto);

		        // Redirige a la página de inicio o a donde desees después de guardar
		        return "redirect:/Inicio";
		    }
		
		private static final Logger logger = LoggerFactory.getLogger(controlador.class);
		
		
		
		
		
	
		/*@GetMapping("/editar/{id}")
	    public String mostrarFormularioEdicion(@PathVariable int id, Model model) {
	        Producto producto = produrepo.findById(id).orElseThrow();
	        //Producto producto = categoriaserv.buscarPorID(id);
	        model.addAttribute("producto", producto);
	        return "editar";
	    }

	    // Mapea la solicitud para procesar la edición
	    @PostMapping("/editar/{id}")
	    public String procesarEdicion(@PathVariable int id, @ModelAttribute Producto producto, RedirectAttributes redirectAttributes) {
	        Producto productoExistente = produrepo.findById(id).orElseThrow();
	        // Actualiza los campos del producto existente con los valores del formulario
	        productoExistente.setDescripcion(producto.getDescripcion());
	        productoExistente.setPrecio_unidad(producto.getPrecio_unidad());
	        productoExistente.setStock(producto.getStock());
	        productoExistente.setImagen(producto.getImagen());
	        productoExistente.setEstado(producto.getEstado());

	        // Guarda los cambios en la base de datos
	        produrepo.save(productoExistente);

	        redirectAttributes.addFlashAttribute("mensaje", "Producto editado exitosamente.");
	        return "redirect:/Inicio";
	    }*/
		
		
		/*@GetMapping("/editar/{id}")
		public ModelAndView editar(@PathVariable int id) {
		    ModelAndView mav = new ModelAndView("editar");
		    Producto producto = categoriaserv.buscarPorID(id);
		    mav.addObject("productos", producto);
		    mav.addObject("error", "No se encontró la plantilla `editar`");
		    return mav;
		}*/
		
		@GetMapping("/editar/{id}")
	    public String editar(@PathVariable int id, Model model) {
	        Producto producto = categoriaserv.buscarPorID(id);
	        if (producto != null) {
	            model.addAttribute("productos", producto);
	            return "editar";
	        } else {
	            // Manejar el caso en el que no se encuentra el producto con el ID dado
	            return "producto-no-encontrado";
	        }
	    }
		
		// Procesa la edición del producto
	    @PostMapping("/editar/{id}")
	    public String salvar(@PathVariable int id, @ModelAttribute Producto producto) {
	        // Actualiza el producto en la base de datos
	    	produrepo.save(producto);
	        return "redirect:/Inicio"; // Redirige a la página de inicio o a donde desees
	    }
		
	    /*@PostMapping("/salvar")
		public String salvar(@Validated Producto p, BindingResult result, Model modelo){
		    if (result.hasErrors()) {
		        modelo.addAttribute("error", "El producto no se pudo guardar.");
		        return "error1";
		    }
		    //categoriaserv.grabar(p);
	        return "redirect:/Inicio"; 
		}*/
	    
	    
		

		
		/*@GetMapping("/editar/{id}")
		public ModelAndView editar(@PathVariable int id) {
		    ModelAndView mav = new ModelAndView("editar");
		    Producto producto = categoriaserv.buscarPorID(id);
		    mav.addObject("productos", producto);
		    return mav;
		}*/
		
		@GetMapping("/ventas")
		public String listarVentas(Model model,Authentication authentication) {	    
		    try {
		        boolean isAdmin = authentication.getAuthorities().stream()
		                .anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"));

		        if (!isAdmin) {
		        	model.addAttribute("error", "No eres administrador");
		            System.out.println("No eres administrador");
		            return "error1";
		        }
		        List<Ventas> ventas = ventaService.buscarTodasLasVentas();
			    model.addAttribute("ventas", ventas);
			    return "listaVentas";
			    
		    } catch (Exception e) {
		    	model.addAttribute("error", "No eres administrador");
		        System.out.println("No eres administrador");
		        return "error1";
		    }
		}
		

}
