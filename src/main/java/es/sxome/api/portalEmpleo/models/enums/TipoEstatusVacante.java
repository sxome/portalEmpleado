package es.sxome.api.portalEmpleo.models.enums;

public enum TipoEstatusVacante {

	ABIERTA("ABIERTA"),
	CERRADA("CERRADA");
	
	private String valor;
	
	TipoEstatusVacante(String valor){
		this.valor = valor;
	}
	
	public String getValor() {
		return valor;
	}
}
