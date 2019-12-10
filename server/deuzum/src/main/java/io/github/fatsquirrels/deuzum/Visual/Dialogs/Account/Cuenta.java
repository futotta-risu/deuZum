package io.github.fatsquirrels.deuzum.Visual.Dialogs.Account;


/**
 * Clase que crea objetos de una cuenta de usuario
 *
 */
public class Cuenta {
	private String idCuenta;
	private String numCuenta;
	private String idUsuario;
	private String dinero;
	private String tipoCuenta;
	private String Descripcion;
	private String Estado;
	private String Categoria;
	
	public String getIdCuenta() {
		return idCuenta;
	}
	public void setIdCuenta(String idCuenta) {
		this.idCuenta = idCuenta;
	}
	public String getNumCuenta() {
		return numCuenta;
	}
	public void setNumCuenta(String numCuenta) {
		this.numCuenta = numCuenta;
	}
	public String getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getDinero() {
		return dinero;
	}
	public void setDinero(String dinero) {
		this.dinero = dinero;
	}
	public String getTipoCuenta() {
		return tipoCuenta;
	}
	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}
	public String getDescripcion() {
		return Descripcion;
	}
	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}
	public String getEstado() {
		return Estado;
	}
	public void setEstado(String estado) {
		Estado = estado;
	}
	public String getCategoria() {
		return Categoria;
	}
	public void setCategoria(String categoria) {
		Categoria = categoria;
	}
	
	public Cuenta(String idCuenta, String numCuenta, String idUsuario, String dinero, String tipoCuenta,
			String descripcion, String estado, String categoria) {
		super();
		this.idCuenta = idCuenta;
		this.numCuenta = numCuenta;
		this.idUsuario = idUsuario;
		this.dinero = dinero;
		this.tipoCuenta = tipoCuenta;
		Descripcion = descripcion;
		Estado = estado;
		Categoria = categoria;
	}
	@Override
	public String toString() {
		return "Cuenta [idCuenta=" + idCuenta + ", numCuenta=" + numCuenta + ", idUsuario=" + idUsuario + ", dinero="
				+ dinero + ", tipoCuenta=" + tipoCuenta + ", Descripcion=" + Descripcion + ", Estado=" + Estado
				+ ", Categoria=" + Categoria + "]";
	}
	
	

	
}
