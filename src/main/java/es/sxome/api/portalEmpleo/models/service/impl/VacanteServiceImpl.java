package es.sxome.api.portalEmpleo.models.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import es.sxome.api.portalEmpleo.exceptions.RequestException;
import es.sxome.api.portalEmpleo.models.dao.VacanteDao;
import es.sxome.api.portalEmpleo.models.dao.base.EntidadDaoBase;
import es.sxome.api.portalEmpleo.models.dto.VacanteConIdDto;
import es.sxome.api.portalEmpleo.models.dto.VacanteDto;
import es.sxome.api.portalEmpleo.models.entity.Categoria;
import es.sxome.api.portalEmpleo.models.entity.Vacante;
import es.sxome.api.portalEmpleo.models.enums.TipoEstatusVacante;
import es.sxome.api.portalEmpleo.models.service.CategoriaService;
import es.sxome.api.portalEmpleo.models.service.VacanteService;
import es.sxome.api.portalEmpleo.utils.Constantes;

@Service
public class VacanteServiceImpl extends AbstractEntidadServiceImpl<Vacante> implements VacanteService {

	private static final String NO_EXISTE_VACANTE = "No existe vacante para el id indicado";
	
	@Autowired
	@Qualifier("vacanteDao")
	private EntidadDaoBase<Vacante> vacanteDao;
	
	@Autowired
	private CategoriaService categoriaService;

	@Transactional(readOnly = true)
	public List<Vacante> mostrarTodos() {
		return getRepository().findAllFetchAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Vacante obtenerVacanteFetchAll(Long id) {
		Assert.notNull(id, "El id no puede ser null");
		return getRepository().findByIdFetchAll(id);
	}
	
	@Override
	public boolean existeNombre(String nombre) {
		Assert.notNull(nombre, "El nombre no puede ser null");
		return getRepository().findFirstByNombre(nombre) != null ? true : false;
	}
	

	@Override
	public Vacante getVacante(Long id) {
		Assert.notNull(id, "El id no puede ser null");
		Vacante vacante= null;
		try {
			vacante = obtener(id);
		} catch (RuntimeException e) {
			throw new RequestException(Constantes.BAD_REQUEST_COD, NO_EXISTE_VACANTE);
		}
		return vacante;
	}
	
	@Override
	public List<Vacante> getVacantePorCategoriaYSalarioMayorQue(Long idCategoria, BigDecimal salario) {
		Assert.notNull(idCategoria, "El idCategoria no puede ser null");
		Assert.notNull(salario, "El salario no puede ser null");
		return getRepository().getVacantePorCategoriaYSalarioMayorQue(idCategoria, salario);
	}

	@Override
	public List<Vacante> findByEstatusAndSalarioAndFechaBetween(TipoEstatusVacante estatus, BigDecimal salario,
			Date fecha1, Date fecha2) {
		Assert.notNull(estatus, "El estatus no puede ser null");
		Assert.notNull(salario, "El salario no puede ser null");
		Assert.notNull(fecha1, "La fecha1 no puede ser null");
		Assert.notNull(fecha2, "La fecha2 no puede ser null");
		return getRepository().findByEstatusAndSalarioAndFechaBetween(estatus, salario, fecha1, fecha2);
	}
	
	@Override
	public void guardaVacante(VacanteDto vacanteDto) {
		Assert.notNull(vacanteDto, "El vacanteDto no puede ser null");
		Vacante vacante = new Vacante();

		vacante.setNombre(vacanteDto.getNombre());
		vacante.setSalario(vacanteDto.getSalario());
		vacante.setDescripcion(vacanteDto.getDescripcion());
		vacante.setDestacado(vacanteDto.isDestacado());
		vacante.setDetalles(vacanteDto.getDetalles());
		vacante.setEstatus(vacanteDto.getEstatus());
		vacante.setFecha(vacanteDto.getFecha());
		vacante.setImagen(vacanteDto.getImagen());

		if(vacanteDto.getIdCategoria() != null) {
			Categoria categoria = categoriaService.getCategoria(vacanteDto.getIdCategoria());
			vacante.setCategoria(categoria);
		}

		guardar(vacante);
	}
	
	@Override
	public Vacante modificarVacante(VacanteConIdDto vacanteDto) {
		Assert.notNull(vacanteDto, "El vacanteDto no puede ser null");

		Vacante vacante = getVacante(vacanteDto.getId());

		vacante.setNombre(vacanteDto.getNombre());
		vacante.setSalario(vacanteDto.getSalario());
		vacante.setDescripcion(vacanteDto.getDescripcion());
		vacante.setDestacado(vacanteDto.isDestacado());
		vacante.setDetalles(vacanteDto.getDetalles());
		vacante.setEstatus(vacanteDto.getEstatus());
		vacante.setFecha(vacanteDto.getFecha());
		vacante.setImagen(vacanteDto.getImagen());

		if(vacanteDto.getIdCategoria() != null) {
			Categoria categoria = categoriaService.getCategoria(vacanteDto.getIdCategoria());
			vacante.setCategoria(categoria);
		}

		return guardar(vacante);
	}
	
	@Override
	public EntidadDaoBase<Vacante> getRepositorioBase() {
		return vacanteDao;
	}

	private VacanteDao getRepository() {
		return ((VacanteDao) getRepositorioBase());
	}

}
