package es.sxome.api.portalEmpleo.models.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import es.sxome.api.portalEmpleo.models.enums.TipoEstatusUsuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USUARIO", uniqueConstraints = { @UniqueConstraint(name = "UK_Usuario_0", columnNames = { "username" })})
public class Usuario extends AbstractEntidadDominio {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2805257231879007838L;

	@NotBlank(message = "Username requerido")
	@Size(min = 1, max = 100, message = "Username debe medir entre 1 y 100")
	private String username;

	@Size(max = 200)
	private String nombre;

	@Email
	private String email;

	@NotBlank(message = "Password requerida")
	@Size(max = 100, message = "Password debe medir entre 1 y 100")
	private String password;

	@Enumerated(EnumType.STRING)
	private TipoEstatusUsuario estatus;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fechaRegistro;

	@ManyToMany
	@JoinTable(name = "USUARIO_PERFIL", joinColumns = @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "ID_PERFIL", referencedColumnName = "ID"))
	private Set<Perfil> perfiles;

}
