package es.sxome.api.portalEmpleo.models.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;

import es.sxome.api.portalEmpleo.models.dao.base.EntidadDaoBase;
import es.sxome.api.portalEmpleo.models.entity.Vacante;
import es.sxome.api.portalEmpleo.models.enums.TipoEstatusVacante;

public interface VacanteDao extends EntidadDaoBase<Vacante>{

	Vacante findFirstByNombre(String nombre);
	
	@Query("SELECT #{#entityName} "
			+ "FROM #{#entityName}  #{#entityName} "
			+ "LEFT JOIN FETCH #{#entityName}.categoria ")
	List<Vacante> findAllFetchAll();
	
	@Query("SELECT #{#entityName} "
			+ "FROM #{#entityName}  #{#entityName} "
			+ "LEFT JOIN FETCH #{#entityName}.categoria "
			+ "WHERE #{#entityName}.id = ?1 ")
	Vacante findByIdFetchAll(Long id);
	
	@Query("SELECT #{#entityName} "
			+ "FROM #{#entityName}  #{#entityName} "
			+ "LEFT JOIN FETCH #{#entityName}.categoria categoria "
			+ "WHERE #{#entityName}.salario > ?1 "
			+ "AND categoria.id = ?2")
	List<Vacante> getVacantePorCategoriaYSalarioMayorQue(Long idCategoria, BigDecimal salario);
	
	List<Vacante> findByEstatusAndSalarioAndFechaBetween(TipoEstatusVacante estatus, BigDecimal salario, Date fecha1, Date fecha2);
}
