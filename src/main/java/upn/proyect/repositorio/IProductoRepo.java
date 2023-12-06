package upn.proyect.repositorio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import upn.proyect.entidades.Categoria;
import upn.proyect.entidades.Producto;


@Repository
public interface IProductoRepo extends JpaRepository<Producto, Integer>{
	//importante
	List<Producto> findByCategoria(Categoria categoria);

}
