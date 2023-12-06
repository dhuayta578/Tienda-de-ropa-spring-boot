package upn.proyect.entidades;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Ventas")
public class Ventas {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;
    
    @Column(name = "usuario", nullable = false)
    private String nombreUsuario;
    
    @Column(name = "fecha_venta", nullable = false)
    private LocalDate fechaVenta;
    
    @Column(name = "monto_total", nullable = false)
    private BigDecimal montoTotal;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public LocalDate getFechaVenta() {
		return fechaVenta;
	}

	public void setFechaVenta(LocalDate fechaVenta) {
		this.fechaVenta = fechaVenta;
	}

	public BigDecimal getMontoTotal() {
		return montoTotal;
	}

	public void setMontoTotal(BigDecimal montoTotal) {
		this.montoTotal = montoTotal;
	}

	@Override
	public String toString() {
		return "Ventas [id=" + id + ", usuario=" + usuario + ", nombreUsuario=" + nombreUsuario + ", fechaVenta="
				+ fechaVenta + ", montoTotal=" + montoTotal + "]";
	}

	public Ventas(int id, Usuario usuario, String nombreUsuario, LocalDate fechaVenta, BigDecimal montoTotal) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.nombreUsuario = nombreUsuario;
		this.fechaVenta = fechaVenta;
		this.montoTotal = montoTotal;
	}

	public Ventas() {
		// TODO Auto-generated constructor stub
	}
    
	
    
	
}
