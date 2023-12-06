package upn.proyect.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import upn.proyect.IServicio.IDetalleServicio;
import upn.proyect.entidades.Detalle;
import upn.proyect.repositorio.IDetalleRepo;
@Service
public class DetalleServicio implements IDetalleServicio{
	
	@Autowired
	private IDetalleRepo detalleRepo;
	
	public void detalleServicio(IDetalleRepo detalleRepo) {
		this.detalleRepo = detalleRepo;
	}

	public List<Detalle> obtenerDetallePorProducto(int idProducto) {
		// TODO Auto-generated method stub
		return detalleRepo.findByProductoId(idProducto);
	}
}
