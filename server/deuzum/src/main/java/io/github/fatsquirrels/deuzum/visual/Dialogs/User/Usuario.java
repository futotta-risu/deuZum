package io.github.fatsquirrels.deuzum.visual.Dialogs.User;

public class Usuario {
	private String id;
	private String usuario;
	private String contrasena;
	private String fecha_creacion;
	private String preg_seguridad;
	private String resp_seguridad;
	private String permisos;
	private String categoria;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getContrasena() {
		return contrasena;
	}
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	public String getFecha_creacion() {
		return fecha_creacion;
	}
	public void setFecha_creacion(String fecha_creacion) {
		this.fecha_creacion = fecha_creacion;
	}
	public String getPreg_seguridad() {
		return preg_seguridad;
	}
	public void setPreg_seguridad(String preg_seguridad) {
		this.preg_seguridad = preg_seguridad;
	}
	public String getResp_seguridad() {
		return resp_seguridad;
	}
	public void setResp_seguridad(String resp_seguridad) {
		this.resp_seguridad = resp_seguridad;
	}
	public String getPermisos() {
		return permisos;
	}
	public void setPermisos(String permisos) {
		this.permisos = permisos;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	public Usuario(String id, String usuario, String contrasena, String fecha_creacion, String preg_seguridad,
			String resp_seguridad, String permisos, String categoria) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.contrasena = contrasena;
		this.fecha_creacion = fecha_creacion;
		this.preg_seguridad = preg_seguridad;
		this.resp_seguridad = resp_seguridad;
		this.permisos = permisos;
		this.categoria = categoria;
	}
	@Override
	public String toString() {
		return "id = " + id + ", usuario = " + usuario + ", contraseña = " + contrasena + ", fecha_creacion = "
				+ fecha_creacion + ", Pregunta seguridad = " + preg_seguridad + ", Respuesta seguridad = " + resp_seguridad
				+ ", Permisos = " + permisos + ", Categoria = " + categoria ;
	}
	
	
	
	
	
	
	
	
}
