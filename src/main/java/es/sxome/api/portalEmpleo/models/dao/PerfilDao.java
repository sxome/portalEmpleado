package es.sxome.api.portalEmpleo.models.dao;

import es.sxome.api.portalEmpleo.models.dao.base.EntidadDaoBase;
import es.sxome.api.portalEmpleo.models.entity.Perfil;

public interface PerfilDao extends EntidadDaoBase<Perfil>{

	Perfil findFirstByPerfil(String perfil);
	
}
