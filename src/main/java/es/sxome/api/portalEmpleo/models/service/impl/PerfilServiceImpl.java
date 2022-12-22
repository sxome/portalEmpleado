package es.sxome.api.portalEmpleo.models.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import es.sxome.api.portalEmpleo.exceptions.RequestException;
import es.sxome.api.portalEmpleo.models.dao.PerfilDao;
import es.sxome.api.portalEmpleo.models.dao.base.EntidadDaoBase;
import es.sxome.api.portalEmpleo.models.entity.Perfil;
import es.sxome.api.portalEmpleo.models.service.PerfilService;
import es.sxome.api.portalEmpleo.utils.Constantes;

@Service
public class PerfilServiceImpl extends AbstractEntidadServiceImpl<Perfil> implements PerfilService {

	private static final String NO_EXISTE_PERFIL = "No existe perfil para el id indicado";

	@Autowired
	@Qualifier("perfilDao")
	private EntidadDaoBase<Perfil> perfilDao;

	@Transactional(readOnly = true)
	public List<Perfil> mostrarTodos() {
		return (List<Perfil>) getRepository().findAll();
	}

	@Override
	public boolean existePerfil(String perfil) {
		Assert.notNull(perfil, "El perfil no puede ser null");
		return getRepository().findFirstByPerfil(perfil) != null ? true : false;
	}

	@Override
	public boolean existePerfil(Set<Perfil> perfilesUsuario) {
		Assert.notNull(perfilesUsuario, "La lista perfilesUsuario no puede ser null");
		boolean respuesta = true;

		List<Perfil> perfiles = mostrarTodos();

		for (Perfil perfilUsuario : perfilesUsuario) {
			if (!perfiles.contains(perfilUsuario)) {
				respuesta = false;
			}
		}

		return respuesta;
	}

	@Override
	public Perfil buscaPerfilPorNombrePerfil(String perfil) {
		return getRepository().findFirstByPerfil(perfil);
	}

	@Override
	public boolean isNombrePerfilValido(Perfil perfilRequest) {
		boolean respuesta = true;
		List<Perfil> perfiles = mostrarTodos();
		for (Perfil perfil : perfiles) {
			// Valida su existe el nombre de perfil recibido para un perfil de base de datos
			// que no sea el que se está modificando
			if ((perfil.getId() != perfilRequest.getId()) && perfil.getPerfil().equals(perfilRequest.getPerfil())) {
				respuesta = false;
				break;
			}
		}
		return respuesta;
	}

	@Override
	public boolean existePerfil(Long id) {
		return getRepository().existsById(id);
	}

	@Override
	public Perfil getPerfil(Long id) {
		Assert.notNull(id, "El id no puede ser null");
		Perfil perfil = null;
		try {
			perfil = obtener(id);
		} catch (RuntimeException e) {
			throw new RequestException(Constantes.BAD_REQUEST_COD, NO_EXISTE_PERFIL);
		}
		return perfil;
	}

	@Override
	public EntidadDaoBase<Perfil> getRepositorioBase() {
		return perfilDao;
	}

	private PerfilDao getRepository() {
		return ((PerfilDao) getRepositorioBase());
	}

}
