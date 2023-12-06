package upn.proyect.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "pedidoscliente")
public class DetallePedidoC implements Serializable {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario_id;
    
    @Column(name = "usuario", nullable = false)
    private String usuario;

    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto_id;
    
    @Column(name = "producto", nullable = false)
    private String producto;
    
    @Column(name = "precio_unidad", nullable = false)
    private double precio_unidad;
    @Column(name = "cantidad", nullable = false)
    private int cantidad;
    @Column(name = "estado", nullable = false)
    private String estado;
    
	
	public DetallePedidoC(int id, Usuario usuario_id, String usuario, Producto producto_id, String producto,
			double precio_unidad, int cantidad, String estado) {
		super();
		this.id = id;
		this.usuario_id = usuario_id;
		this.usuario = usuario;
		this.producto_id = producto_id;
		this.producto = producto;
		this.precio_unidad = precio_unidad;
		this.cantidad = cantidad;
		this.estado = estado;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Usuario getUsuario_id() {
		return usuario_id;
	}


	public void setUsuario_id(Usuario usuario_id) {
		this.usuario_id = usuario_id;
	}


	public String getUsuario() {
		return usuario;
	}


	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}


	public Producto getProducto_id() {
		return producto_id;
	}


	public void setProducto_id(Producto producto_id) {
		this.producto_id = producto_id;
	}


	public String getProducto() {
		return producto;
	}


	public void setProducto(String producto) {
		this.producto = producto;
	}


	public double getPrecio_unidad() {
		return precio_unidad;
	}


	public void setPrecio_unidad(double precio_unidad) {
		this.precio_unidad = precio_unidad;
	}


	public int getCantidad() {
		return cantidad;
	}


	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}


	public String getEstado() {
		return estado;
	}


	public void setEstado(String estado) {
		this.estado = estado;
	}


	@Override
	public String toString() {
		return "DetallePedidoC [id=" + id + ", usuario_id=" + usuario_id + ", usuario=" + usuario + ", producto_id="
				+ producto_id + ", producto=" + producto + ", precio_unidad=" + precio_unidad + ", cantidad=" + cantidad
				+ ", estado=" + estado + "]";
	}


	public DetallePedidoC() {
	}
}
