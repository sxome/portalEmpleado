package es.sxome.api.portalEmpleo.models.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.sxome.api.portalEmpleo.exceptions.RequestException;
import es.sxome.api.portalEmpleo.models.dao.base.EntidadDaoBase;
import es.sxome.api.portalEmpleo.models.entity.AbstractEntidadDominio;
import es.sxome.api.portalEmpleo.utils.Constantes;

@Service
public abstract class AbstractEntidadServiceImpl<T extends AbstractEntidadDominio> {
	
	@Transactional(readOnly = true)
	public T obtener(Long id) {
		return getRepositorioBase().findById(id).orElseThrow(() -> {
			throw new RuntimeException();
		});
	}
	
	public T guardar(T entidad) {
		return getRepositorioBase().save(entidad);
	}

	public boolean eliminar(Long id) {
		try {
			getRepositorioBase().deleteById(id);
			return true;
		} catch (Exception e) {
			throw new RequestException(Constantes.BAD_REQUEST_COD, "No se elimina por que existe una restriccion de integridad");
		}
	}

	public abstract EntidadDaoBase<T> getRepositorioBase();
	
}
