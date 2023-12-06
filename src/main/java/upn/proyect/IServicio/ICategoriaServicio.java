package upn.proyect.IServicio;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import upn.proyect.entidades.Categoria;
import upn.proyect.entidades.Producto;



public interface ICategoriaServicio {
	public List<Categoria>listar();
	public List<Producto>listarP();
	public Optional<Categoria> CategoriaxProducto(int id);
	public Categoria buscarCategoria(int id);
	//importante
	public List<Producto> ProductoPorCategoria(Categoria categoria); 
	public Producto buscarPorID(int id);
	public int grabar(Producto p);
	public int guardarCategoria(Categoria categoria);
}
