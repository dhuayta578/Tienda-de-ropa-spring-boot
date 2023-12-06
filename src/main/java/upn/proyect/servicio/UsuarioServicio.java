package upn.proyect.servicio;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import upn.proyect.IServicio.IUsuarioServicio;
import upn.proyect.entidades.Categoria;
import upn.proyect.entidades.Rol;
import upn.proyect.entidades.Usuario;
import upn.proyect.repositorio.IUsuarioRepo;

@Service
public class UsuarioServicio implements IUsuarioServicio {
	
	@Override
	public List<Usuario> listarU() {
		return (List<Usuario>)usuarioRepo.findAll();
	}
	
	@Autowired
    private IUsuarioRepo usuarioRepo;
	
	public UsuarioServicio(IUsuarioRepo usuarioRepo) {
        this.usuarioRepo = usuarioRepo;
    }
	
	@Override
	public Usuario buscarPorID(int id) {
		return usuarioRepo.findById(id).orElse(null);
	}

	public Usuario obtenerUsuarioPorId(int idUsuario) {
		// TODO Auto-generated method stub
		return usuarioRepo.findById(idUsuario).orElse(null);
	}

	public List<Integer> obtenerListaIdsUsuarios() {
		// TODO Auto-generated method stub
		List<Usuario> usuarios = usuarioRepo.findAll();
	    List<Integer> idsUsuarios = usuarios.stream().map(Usuario::getId).collect(Collectors.toList());
	    return idsUsuarios;
	}
	
}
