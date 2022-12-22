package es.sxome.api.portalEmpleo.models.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;

import es.sxome.api.portalEmpleo.models.dao.base.EntidadDaoBase;
import es.sxome.api.portalEmpleo.models.entity.Usuario;
import es.sxome.api.portalEmpleo.models.enums.TipoEstatusUsuario;

public interface UsuarioDao extends EntidadDaoBase<Usuario>{

	@Query("SELECT #{#entityName} "
			+ "FROM #{#entityName}  #{#entityName} "
			+ "LEFT JOIN FETCH #{#entityName}.perfiles ")
	List<Usuario> findAllFetchAll();
	
	@Query("SELECT #{#entityName} "
			+ "FROM #{#entityName}  #{#entityName} "
			+ "LEFT JOIN FETCH #{#entityName}.perfiles "
			+ "WHERE #{#entityName}.id = ?1 ")
	Usuario findByIdFetchAll(Long id);
	
	@Query("SELECT #{#entityName} "
			+ "FROM #{#entityName}  #{#entityName} "
			+ "LEFT JOIN FETCH #{#entityName}.perfiles "
			+ "WHERE #{#entityName}.username = ?1 ")
	Usuario findByUsernameFetchAll(String username);
	
	Usuario findFirstByUsername(String username);

	List<Usuario> findByEstatusAndFechaRegistroGreaterThan(TipoEstatusUsuario estatus, Date fechaRegistro);
	
	boolean existsById(Long id);

}
