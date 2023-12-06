package upn.proyect.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import upn.proyect.entidades.DetallePedidoC;

@Repository
public interface IDetallePedidoRepo extends JpaRepository<DetallePedidoC, Integer>{
	
}
