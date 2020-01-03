package io.github.fatsquirrels.deuzum.visual.Dialogs.Transactions;

@Deprecated
public class Transacion {
	private String codigo;
	private String idOrigen;
	private String idDestino;
	private String cantidad;
	private String fecha;
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getIdOrigen() {
		return idOrigen;
	}
	public void setIdOrigen(String idOrigen) {
		this.idOrigen = idOrigen;
	}
	public String getIdDestino() {
		return idDestino;
	}
	public void setIdDestino(String idDestino) {
		this.idDestino = idDestino;
	}
	public String getCantidad() {
		return cantidad;
	}
	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public Transacion(String codigo, String idOrigen, String idDestino, String cantidad, String fecha) {
		super();
		this.codigo = codigo;
		this.idOrigen = idOrigen;
		this.idDestino = idDestino;
		this.cantidad = cantidad;
		this.fecha = fecha;
	}
	@Override
	public String toString() {
		return "codigo=" + codigo + ", idOrigen=" + idOrigen + ", idDestino=" + idDestino + ", cantidad="
				+ cantidad + ", fecha=" + fecha ;
	}
	
	
	
	



	

}
