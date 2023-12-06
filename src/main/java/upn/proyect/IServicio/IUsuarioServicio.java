package upn.proyect.IServicio;

import java.util.List;


import upn.proyect.entidades.Usuario;

public interface IUsuarioServicio {

    public Usuario buscarPorID(int id);
    public List<Usuario>listarU();
}
