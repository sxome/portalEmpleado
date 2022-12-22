package es.sxome.api.portalEmpleo.models.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import es.sxome.api.portalEmpleo.exceptions.RequestException;
import es.sxome.api.portalEmpleo.models.dao.CategoriaDao;
import es.sxome.api.portalEmpleo.models.dao.base.EntidadDaoBase;
import es.sxome.api.portalEmpleo.models.entity.Categoria;
import es.sxome.api.portalEmpleo.models.service.CategoriaService;
import es.sxome.api.portalEmpleo.utils.Constantes;

@Service
public class CategoriaServiceImpl extends AbstractEntidadServiceImpl<Categoria> implements CategoriaService {

	private static final String NO_EXISTE_CATEGORIA = "No existe categoria para el id indicado";
	
	@Autowired
	@Qualifier("categoriaDao")
	private EntidadDaoBase<Categoria> categoriaDao;
	
	@Override
	public List<Categoria> mostrarTodos() {
		return (List<Categoria>) getRepository().findAll();
	}
	
	@Override
	public boolean existeNombre(String nombre) {
		Assert.notNull(nombre, "El nombre no puede ser null");
		return getRepository().findFirstByNombre(nombre) != null ? true : false;
	}
	
	@Override
	public Categoria getCategoria(Long id) {
		Assert.notNull(id, "El id no puede ser null");
		Categoria categoria= null;
		try {
			categoria = obtener(id);
		} catch (RuntimeException e) {
			throw new RequestException(Constantes.BAD_REQUEST_COD, NO_EXISTE_CATEGORIA);
		}
		return categoria;
	}
	
	@Override
	public Categoria modificarCategoria(Categoria categoriaModif) {
		Assert.notNull(categoriaModif, "El categoriaModif no puede ser null");

		Categoria categoria = getCategoria(categoriaModif.getId());

		categoria.setNombre(categoriaModif.getNombre());
		categoria.setDescripcion(categoriaModif.getDescripcion());

		return guardar(categoria);
	}
	
	@Override
	public EntidadDaoBase<Categoria> getRepositorioBase() {
		return categoriaDao;
	}

	private CategoriaDao getRepository() {
		return ((CategoriaDao) getRepositorioBase());
	}

}
