package es.sxome.api.portalEmpleo.models.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "INSCRIPCION", uniqueConstraints = { @UniqueConstraint(name = "UK_Inscripcion_0", columnNames = { "id_usuario", "id_vacante" })})
public class Inscripcion extends AbstractEntidadDominio{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2818745933480148387L;
	
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID")
	private Usuario usuario;
	
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_VACANTE", referencedColumnName = "ID")
	private Vacante vacante;
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaInscripcion;

}
