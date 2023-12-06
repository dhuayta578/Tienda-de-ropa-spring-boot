package upn.proyect.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Carrito {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	@JoinColumn(name="producto_id")
	private Producto Idproducto;
	private int Cantidad;
	
	public Carrito() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Carrito [id=" + id + ", Idproducto=" + Idproducto + ", Cantidad=" + Cantidad + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Producto getIdproducto() {
		return Idproducto;
	}

	public void setIdproducto(Producto idproducto) {
		Idproducto = idproducto;
	}

	public int getCantidad() {
		return Cantidad;
	}

	public void setCantidad(int cantidad) {
		Cantidad = cantidad;
	}
	
	
	
}
