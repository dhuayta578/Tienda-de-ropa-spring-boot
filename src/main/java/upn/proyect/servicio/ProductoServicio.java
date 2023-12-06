package upn.proyect.servicio;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import upn.proyect.IServicio.IProductoServicio;
import upn.proyect.entidades.Producto;
import upn.proyect.repositorio.IProductoRepo;

@Service
public class ProductoServicio implements IProductoServicio {
	@Autowired
	private IProductoRepo productoRepo;

	public List<Integer> obtenerListaIdProducto() {
		List<Producto> productos = productoRepo.findAll();
	    List<Integer> idsProductos = productos.stream().map(Producto::getId).collect(Collectors.toList());
	    return idsProductos;
	}

	public Producto obtenerProductoPorId(int idProducto) {
		// TODO Auto-generated method stub
		return productoRepo.findById(idProducto).orElse(null);
	}

}
