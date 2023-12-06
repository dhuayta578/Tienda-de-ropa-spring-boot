package upn.proyect.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import upn.proyect.entidades.Ventas;


@Repository
public interface IVentasRepo extends JpaRepository<Ventas, Integer>{
	Ventas save(Ventas venta);
	List<Ventas> findByUsuarioId(int usuarioId);
}
