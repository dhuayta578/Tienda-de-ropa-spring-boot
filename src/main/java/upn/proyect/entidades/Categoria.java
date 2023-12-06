package upn.proyect.entidades;

import javax.persistence.Entity;
import javax.persistence.GenerationType;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name="categorias")
public class Categoria implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String Descripcion;
	private String Imagen;
	private char Estado;
	
	public Categoria() {
		// TODO Auto-generated constructor stub
	}

	public Categoria(int id, String descripcion, String imagen, char estado) {
		super();
		this.id = id;
		Descripcion = descripcion;
		Imagen = imagen;
		Estado = estado;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescripcion() {
		return Descripcion;
	}

	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
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
		return "Categoria [id=" + id + ", Descripcion=" + Descripcion + ", Imagen=" + Imagen + ", Estado=" + Estado
				+ "]";
	}


	
}
