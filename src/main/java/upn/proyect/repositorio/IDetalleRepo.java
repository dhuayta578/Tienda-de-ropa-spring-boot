package upn.proyect.repositorio;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import upn.proyect.entidades.Detalle;
import upn.proyect.entidades.Ventas;



@Repository
public interface IDetalleRepo extends JpaRepository<Detalle, Integer>{
	Detalle save(Detalle detalle);
	List<Detalle> findByProductoId(int usuarioId);
}
