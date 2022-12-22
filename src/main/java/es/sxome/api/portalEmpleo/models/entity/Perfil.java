package es.sxome.api.portalEmpleo.models.entity;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
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
@Table(name = "PERFIL", uniqueConstraints = { @UniqueConstraint(name = "UK_Perfil_0", columnNames = { "perfil" })})
public class Perfil extends AbstractEntidadDominio{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5827241712181735966L;

	@NotBlank(message = "Perfil requerido")
	@Size(max = 20, message = "No se pueden introducir mas de 20 caracteres en el campo perfil")
	private String perfil;

	@Override
	public int hashCode() {
		return Objects.hash(perfil);
	}

	@Override
	public boolean equals(Object obj) {
		boolean respuesta = false;
	if(obj instanceof Perfil) {

		Perfil otro = (Perfil)obj;
		respuesta = (this.perfil.equals(otro.perfil));
	}
	return respuesta;
	}

}
