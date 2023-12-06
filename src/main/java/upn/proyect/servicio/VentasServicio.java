package upn.proyect.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import upn.proyect.IServicio.IVentasServicio;
import upn.proyect.entidades.Ventas;
import upn.proyect.repositorio.IVentasRepo;

@Service
public class VentasServicio implements IVentasServicio {
	
	@Autowired
	private IVentasRepo ventaRepo;
	
	public void ventaServicio(IVentasRepo ventaRepo) {
        this.ventaRepo = ventaRepo;
    }
	
	public List<Ventas> buscarTodasLasVentas() {
	    return ventaRepo.findAll();
	}

	public List<Ventas> obtenerVentasPorUsuario(int idUsuario) {
		// TODO Auto-generated method stub
		return ventaRepo.findByUsuarioId(idUsuario);
	}


}
