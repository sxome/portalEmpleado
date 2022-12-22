package es.sxome.api.portalEmpleo.exceptions;

public class RequestException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 528826559094433897L;
	
	private String codigo;
	
	public RequestException(String codigo, String mensaje) {
		super(mensaje);
		this.codigo = codigo;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	
}
