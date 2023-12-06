package upn.proyect.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import upn.proyect.entidades.Carrito;
import upn.proyect.entidades.Rol;

@Repository
public interface IRolRepo extends JpaRepository<Rol, Integer>{

}
