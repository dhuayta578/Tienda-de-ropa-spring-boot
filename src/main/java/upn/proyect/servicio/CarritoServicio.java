package upn.proyect.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import upn.proyect.IServicio.ICarritoServicio;
import upn.proyect.entidades.Carrito;
import upn.proyect.repositorio.ICarritoRepo;

@Service
public class CarritoServicio implements ICarritoServicio {
	
	@Autowired
	private ICarritoRepo carritoRepo;
	
	@Override
	public Carrito buscarProducto(int Idproducto) {
		// TODO Auto-generated method stub
		return carritoRepo.findById(Idproducto).orElse(null);
	}

}
