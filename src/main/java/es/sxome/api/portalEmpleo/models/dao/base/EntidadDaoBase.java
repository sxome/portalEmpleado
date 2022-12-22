package es.sxome.api.portalEmpleo.models.dao.base;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import es.sxome.api.portalEmpleo.models.entity.AbstractEntidadDominio;

@NoRepositoryBean
public interface EntidadDaoBase<T extends AbstractEntidadDominio> extends CrudRepository<T, Long>{

//	T findByIdFetchAll(Long id);
}
