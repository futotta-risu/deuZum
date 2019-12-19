package io.github.fatsquirrels.deuzum.visual.Dialogs.Group;

public class Grupo {
	private String idGrupo;
	private String nombreGrupo;
	private String descripcion;
	
	
	
	public String getIdGrupo() {
		return idGrupo;
	}



	public void setIdGrupo(String idGrupo) {
		this.idGrupo = idGrupo;
	}



	public String getNombreGrupo() {
		return nombreGrupo;
	}



	public void setNombreGrupo(String nombreGrupo) {
		this.nombreGrupo = nombreGrupo;
	}



	public String getDescripcion() {
		return descripcion;
	}



	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}



	public Grupo(String idGrupo, String nombreGrupo, String descripcion) {
		super();
		this.idGrupo = idGrupo;
		this.nombreGrupo = nombreGrupo;
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "Grupo [idGrupo=" + idGrupo + ", nombreGrupo=" + nombreGrupo + ", descripcion=" + descripcion + "]";
	}


	
	
	
}
