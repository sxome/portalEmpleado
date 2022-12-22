package es.sxome.api.portalEmpleo.models.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import es.sxome.api.portalEmpleo.models.entity.interfaces.IEntidadDominio;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class AbstractEntidadDominio implements IEntidadDominio<Long>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1053548646240390220L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

}
