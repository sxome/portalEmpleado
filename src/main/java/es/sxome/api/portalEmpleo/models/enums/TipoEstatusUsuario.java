package es.sxome.api.portalEmpleo.models.enums;

public enum TipoEstatusUsuario {

	EMPLEADO("EMPLEADO"),
	DESEMPLEADO("DESEMPLEADO");
	
	private String valor;
	
	TipoEstatusUsuario(String valor){
		this.valor = valor;
	}
	
	public String getValor() {
		return valor;
	}
}
