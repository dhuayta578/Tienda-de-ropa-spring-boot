package upn.proyect.entidades;

import java.io.Serializable;
import java.util.HashSet;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "productos")
public class Producto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	@JoinColumn(name="categoria_id")
	private Categoria categoria;
	private String Descripcion;
	private double precio_unidad;
	private int Stock;
	private String Imagen;
	private char Estado;

	public Producto() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public String getDescripcion() {
		return Descripcion;
	}

	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}

	public double getPrecio_unidad() {
		return precio_unidad;
	}

	public void setPrecio_unidad(double precio_unidad) {
		this.precio_unidad = precio_unidad;
	}

	public int getStock() {
		return Stock;
	}

	public void setStock(int stock) {
		Stock = stock;
	}

	public String getImagen() {
		return Imagen;
	}

	public void setImagen(String imagen) {
		Imagen = imagen;
	}

	public char getEstado() {
		return Estado;
	}

	public void setEstado(char estado) {
		Estado = estado;
	}

	@Override
	public String toString() {
		return "Producto [id=" + id + ", categoria=" + categoria + ", Descripcion=" + Descripcion + ", precio_unidad="
				+ precio_unidad + ", Stock=" + Stock + ", Imagen=" + Imagen + ", Estado=" + Estado + "]";
	}

	public Producto(int id, Categoria categoria, String descripcion, double precio_unidad, int stock, String imagen,
			char estado) {
		super();
		this.id = id;
		this.categoria = categoria;
		Descripcion = descripcion;
		this.precio_unidad = precio_unidad;
		Stock = stock;
		Imagen = imagen;
		Estado = estado;
	}

	
}
