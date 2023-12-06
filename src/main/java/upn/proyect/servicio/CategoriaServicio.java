package upn.proyect.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import upn.proyect.IServicio.ICategoriaServicio;
import upn.proyect.controlador.controlador;
import upn.proyect.entidades.Categoria;
import upn.proyect.entidades.Producto;
import upn.proyect.repositorio.ICategoriaRepo;
import upn.proyect.repositorio.IProductoRepo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
public class CategoriaServicio implements ICategoriaServicio {
	
	@Autowired
	private ICategoriaRepo categoriaRepo;
	@Autowired
	private IProductoRepo productoRepo;
	
	@Override
	public List<Categoria> listar() {
		return (List<Categoria>)categoriaRepo.findAll();
	}
	
	@Override
	public List<Producto> listarP() {
		return (List<Producto>)productoRepo.findAll();
	}
	
	private static final Logger logger = LoggerFactory.getLogger(controlador.class);
	
	@Override
	public int grabar(Producto p) {
		/*int res = 0;
		Producto ObjA = productoRepo.save(p);
		if(!ObjA.equals(null)) {
			res = 1;
		}
		return res;*/
		
		 try {
		        Producto ObjA = productoRepo.save(p);
		        if (ObjA != null) {
		            return 1; // Éxito
		        } else {
		            return 0; // Fallo
		        }
		    } catch (Exception e) {
		        // Manejar errores de la base de datos
		        logger.error("Error al guardar el producto: " + e.getMessage(), e);
		        return 0; // Fallo
		    }
	}

	@Override
	public Optional<Categoria> CategoriaxProducto(int id) {
		return (Optional<Categoria>) categoriaRepo.findById(id);
	}
	//importante
	@Override
	public List<Producto> ProductoPorCategoria(Categoria categoria) {
		return productoRepo.findByCategoria(categoria);
	}

	@Override
	public Producto buscarPorID(int id) {
		return productoRepo.findById(id).orElse(null);
	}

	//importante
	@Override
	public Categoria buscarCategoria(int id) {
		return categoriaRepo.findById(id).orElse(null);
	}

	@Override
	public int guardarCategoria(Categoria categoria) {
		try {
            Categoria categoriaGuardada = categoriaRepo.save(categoria);
            if (categoriaGuardada != null) {
                return categoriaGuardada.getId();
            } else {
                return 0; // Indicar que no se pudo guardar la categoría
            }
        } catch (Exception e) {
            // Manejar la excepción en caso de error
            e.printStackTrace();
            return 0; // Indicar que no se pudo guardar la categoría
        }
	}
	
	
	public List<Categoria> buscarPorIDs(List<Integer> ids) {
        return categoriaRepo.findAllById(ids);
    }
	
	
}
