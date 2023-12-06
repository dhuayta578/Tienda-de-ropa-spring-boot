package upn.proyect.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import upn.proyect.entidades.Carrito;

@Repository
public interface ICarritoRepo extends JpaRepository<Carrito, Integer>{

}
