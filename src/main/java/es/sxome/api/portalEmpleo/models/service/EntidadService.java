package es.sxome.api.portalEmpleo.models.service;

import es.sxome.api.portalEmpleo.models.entity.AbstractEntidadDominio;

public interface EntidadService<T extends AbstractEntidadDominio> {
	
	T obtener(Long id);
	
	T guardar(T entidad);
		
	boolean eliminar(Long id);

}
