package upn.proyect;

import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import upn.proyect.entidades.Producto;
import upn.proyect.entidades.Categoria;

@Repository
public interface productoRepo extends JpaRepository<Producto, Integer>{
	List<Producto> findByCategoria(Categoria categoria);
}
