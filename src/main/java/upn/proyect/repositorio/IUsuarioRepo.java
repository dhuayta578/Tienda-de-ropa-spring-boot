package upn.proyect.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import upn.proyect.entidades.Usuario;
@Repository
public interface IUsuarioRepo extends JpaRepository<Usuario, Integer>{
	Usuario findByUsuario(String usuario);
	
}
