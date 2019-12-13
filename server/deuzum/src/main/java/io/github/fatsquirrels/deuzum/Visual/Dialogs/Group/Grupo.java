package io.github.fatsquirrels.deuzum.visual.Dialogs.Group;

public class Grupo {
	private String idGrupo;
	private String nombreGrupo;
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
	
	public Grupo(String idGrupo, String nombreGrupo) {
		this.idGrupo = idGrupo;
		this.nombreGrupo = nombreGrupo;
	}

	public String toString() {
		return "Grupo [idGrupo=" + idGrupo + ", nombreGrupo=" + nombreGrupo + "]";
	}
	
	
	
	
}
