package es.sxome.api.portalEmpleo.models.dao;

import es.sxome.api.portalEmpleo.models.dao.base.EntidadDaoBase;
import es.sxome.api.portalEmpleo.models.entity.Categoria;

public interface CategoriaDao extends EntidadDaoBase<Categoria>{

	Categoria findFirstByNombre(String nombre);

}
