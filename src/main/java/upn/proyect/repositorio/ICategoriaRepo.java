package upn.proyect.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import upn.proyect.entidades.Categoria;
import upn.proyect.entidades.Producto;

@Repository
public interface ICategoriaRepo extends JpaRepository<Categoria, Integer>{
	

}
