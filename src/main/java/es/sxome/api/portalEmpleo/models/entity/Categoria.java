package es.sxome.api.portalEmpleo.models.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CATEGORIA")
public class Categoria extends AbstractEntidadDominio{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5815267358024165079L;
	
	@NotBlank
	@Size(max = 200, message = "No se pueden introducir mas de 200 caracteres en el campo nombre")
	private String nombre;

	@NotBlank
	@Size(max = 500, message = "No se pueden introducir mas de 500 caracteres en el campo descripcion")
	private String descripcion;

	// prueba para CRQ_1
}
