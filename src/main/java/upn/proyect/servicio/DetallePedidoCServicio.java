package upn.proyect.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import upn.proyect.IServicio.IDetallePedidoServicio;
import upn.proyect.entidades.DetallePedidoC;
import upn.proyect.repositorio.IDetallePedidoRepo;

@Service
public class DetallePedidoCServicio implements IDetallePedidoServicio {
	@Autowired
	private IDetallePedidoRepo detallePedidoRepo;
	
	public void detallePedido(IDetallePedidoRepo detallePedidoRepo) {
        this.detallePedidoRepo = detallePedidoRepo;
    }
	
	public List<DetallePedidoC> listar() {
		return (List<DetallePedidoC>)detallePedidoRepo.findAll();
	}
	
	
	public List<DetallePedidoC> buscarTodasLosPedidos() {
	    return detallePedidoRepo.findAll();
	}


	

}
