package es.sxome.api.portalEmpleo.models.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import es.sxome.api.portalEmpleo.models.dto.VacanteConIdDto;
import es.sxome.api.portalEmpleo.models.dto.VacanteDto;
import es.sxome.api.portalEmpleo.models.entity.Vacante;
import es.sxome.api.portalEmpleo.models.enums.TipoEstatusVacante;

public interface VacanteService extends EntidadService<Vacante>{

	List<Vacante> mostrarTodos();
	
	Vacante obtenerVacanteFetchAll(Long id);
	
	boolean existeNombre(String nombre);
	
	List<Vacante> getVacantePorCategoriaYSalarioMayorQue(Long idCategoria, BigDecimal salario);
	
	List<Vacante> findByEstatusAndSalarioAndFechaBetween(TipoEstatusVacante estatus, BigDecimal salario, Date fecha1, Date fecha2);

	Vacante getVacante(Long id);

	void guardaVacante(VacanteDto vacanteDto);

	Vacante modificarVacante(VacanteConIdDto vacanteDto);

}
