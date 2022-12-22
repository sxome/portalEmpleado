package es.sxome.api.portalEmpleo.models.service.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import es.sxome.api.portalEmpleo.exceptions.RequestException;
import es.sxome.api.portalEmpleo.models.dao.UsuarioDao;
import es.sxome.api.portalEmpleo.models.dao.base.EntidadDaoBase;
import es.sxome.api.portalEmpleo.models.dto.PerfilDto;
import es.sxome.api.portalEmpleo.models.dto.UsuarioDto;
import es.sxome.api.portalEmpleo.models.entity.Perfil;
import es.sxome.api.portalEmpleo.models.entity.Usuario;
import es.sxome.api.portalEmpleo.models.enums.TipoEstatusUsuario;
import es.sxome.api.portalEmpleo.models.service.PerfilService;
import es.sxome.api.portalEmpleo.models.service.UsuarioService;
import es.sxome.api.portalEmpleo.utils.Constantes;

@Service("usuarioService")
public class UsuarioServiceImpl extends AbstractEntidadServiceImpl<Usuario> implements UsuarioService {

	private static final String PERFIL_INEXISTENTE = "Al menos uno de los perfiles introducidos no existe";
	private static final String USERNAME_REPETIDO = "Ya existe un usuario con ese Username";
	private static final String NO_EXISTE_USUARIO = "No existe usuario para el id indicado";
	
	@Autowired
	@Qualifier("usuarioDao")
	private EntidadDaoBase<Usuario> usuarioDao;
	
	@Autowired
	private PerfilService perfilService;

	@Transactional(readOnly = true)
	public List<Usuario> mostrarTodos() {
		return getRepository().findAllFetchAll();
	}

	@Override
	public Usuario obtenerUsuarioFetchAll(Long id) {
		Assert.notNull(id, "El id no puede ser null");
		return getRepository().findByIdFetchAll(id);
	}

	@Override
	public Usuario getUsuario(String username) {
		Assert.notNull(username, "El username no puede ser null");
		return getRepository().findFirstByUsername(username);
	}
	
	@Override
	public Usuario getUsuario(Long id) {
		Assert.notNull(id, "El id no puede ser null");
		Usuario usuario= null;
		try {
			usuario = obtener(id);
		} catch (RuntimeException e) {
			throw new RequestException(Constantes.BAD_REQUEST_COD, NO_EXISTE_USUARIO);
		}
		return usuario;
	}
	
	@Override
	public List<Usuario> obtenerUsuariosPorEstatusYFechaRegistroMayorQue(TipoEstatusUsuario estatus, Date fecha) {
		Assert.notNull(estatus, "El estatus no puede ser null");
		Assert.notNull(fecha, "La fecha no puede ser null");
		return getRepository().findByEstatusAndFechaRegistroGreaterThan(estatus, fecha);
	}

	@Override
	public void guardaUsuario(Long id, UsuarioDto usuarioDto) {
		Assert.notNull(usuarioDto, "El usuarioDto no puede ser null");
		Usuario usuario = new Usuario();
		if(id != null) {
			usuario.setId(id);
		}
		usuario.setUsername(usuarioDto.getUsername());
		usuario.setPassword(usuarioDto.getPassword());
		usuario.setNombre(usuarioDto.getNombre());
		usuario.setFechaRegistro(usuarioDto.getFechaRegistro());
		usuario.setEstatus(usuarioDto.getEstatus());
		usuario.setEmail(usuarioDto.getEmail());

		// Creamos una lista de perfiles en caso de que recibamos perfiles en el RequestBody
		if (usuarioDto.getPerfiles() != null && !usuarioDto.getPerfiles().isEmpty()) {
			Set<Perfil> perfiles = getPerfilesUsuarioRecibidos(usuarioDto.getPerfiles());

			addPerfilesRecibidosAUsuario(perfiles, usuario);
		}
		guardar(usuario);
	}

	private Set<Perfil> getPerfilesUsuarioRecibidos(Set<PerfilDto> perfilesDto) {
		Set<Perfil> perfiles = new HashSet<>();
		Perfil perfil = null;

		for (PerfilDto perfilDto : perfilesDto) {
			perfil = perfilService.buscaPerfilPorNombrePerfil(perfilDto.getPerfil());
			if (perfil != null) {
				perfiles.add(perfil);
			}
		}

		if (perfiles.size() != perfilesDto.size()) {
			throw new RequestException(Constantes.BAD_REQUEST_COD, PERFIL_INEXISTENTE);
		}

		return perfiles;
	}

	private void addPerfilesRecibidosAUsuario(Set<Perfil> perfiles, Usuario usuario) {
		if (!perfiles.isEmpty()) {
			usuario.setPerfiles(perfiles);
		} else {
			throw new RequestException(Constantes.BAD_REQUEST_COD, PERFIL_INEXISTENTE);
		}
	}

	@Override
	public boolean existeUsuario(Long id) {
		Assert.notNull(id, "El id no puede ser null");
		return getRepository().existsById(id);
	}
	
	@Override
	public void validaUsername(String username) {
		Assert.notNull(username, "El username no puede ser null");
		if(getRepository().findFirstByUsername(username) != null) {
			throw new RequestException(Constantes.BAD_REQUEST_COD, USERNAME_REPETIDO);
		}
	}
	
	@Override
	public Usuario devuelveUsuarioValidado(String username) {
		Usuario usuario = getUsuario(username);
		if (usuario == null) {
			throw new RequestException(Constantes.BAD_REQUEST_COD, USERNAME_REPETIDO);
		}
		return usuario;
	}
	
	@Override
	public EntidadDaoBase<Usuario> getRepositorioBase() {
		return usuarioDao;
	}

	private UsuarioDao getRepository() {
		return ((UsuarioDao) getRepositorioBase());
	}

}
