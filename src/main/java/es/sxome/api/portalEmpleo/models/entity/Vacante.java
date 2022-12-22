package es.sxome.api.portalEmpleo.models.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import es.sxome.api.portalEmpleo.models.enums.TipoEstatusVacante;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "VACANTE")
public class Vacante extends AbstractEntidadDominio{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1646089991883947227L;
	
	@NotBlank(message = "Nombre requerido")
	@Size(max = 200, message = "No se pueden introducir mas de 200 caracteres en el campo nombre")
	private String nombre;

	@NotBlank(message = "Descripción requerido")
	@Size(max = 500, message = "No se pueden introducir mas de 500 caracteres en el campo descripción")
	private String descripcion;

	@NotNull
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fecha;
	
	@Min(value = 15000, message = "No se puede introducir un salario menor de 15000€")
	@Max(value = 999999, message = "No se puede introducir un salario mayor de 999999€")
	private BigDecimal salario;
	
	private boolean destacado;
	
	@Size(max = 500)
	private String imagen;
	
	@Enumerated(EnumType.STRING)
	private TipoEstatusVacante estatus;
	
	@Lob
	private String detalles;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "ID_CATEGORIA", referencedColumnName = "ID")
	private Categoria categoria;

}
