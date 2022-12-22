package es.sxome.api.portalEmpleo.models.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import es.sxome.api.portalEmpleo.exceptions.RequestException;
import es.sxome.api.portalEmpleo.models.dao.InscripcionDao;
import es.sxome.api.portalEmpleo.models.dao.base.EntidadDaoBase;
import es.sxome.api.portalEmpleo.models.dto.InscripcionConIdDto;
import es.sxome.api.portalEmpleo.models.dto.InscripcionDto;
import es.sxome.api.portalEmpleo.models.entity.Inscripcion;
import es.sxome.api.portalEmpleo.models.entity.Usuario;
import es.sxome.api.portalEmpleo.models.entity.Vacante;
import es.sxome.api.portalEmpleo.models.service.InscripcionService;
import es.sxome.api.portalEmpleo.models.service.UsuarioService;
import es.sxome.api.portalEmpleo.models.service.VacanteService;
import es.sxome.api.portalEmpleo.utils.Constantes;

@Service
public class InscripcionServiceImpl extends AbstractEntidadServiceImpl<Inscripcion> implements InscripcionService {

	private static final String NO_EXISTE_INSCRIPCION = "No existe inscripcion para el id indicado";
	private static final String YA_EXISTE = "Ya existe una inscripcion para ese usuario en la vacante indicada";

	@Autowired
	@Qualifier("inscripcionDao")
	private EntidadDaoBase<Inscripcion> inscripcionDao;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private VacanteService vacanteService;

	@Override
	public List<Inscripcion> mostrarTodos() {
		return (List<Inscripcion>) getRepository().findAll();
	}

	@Override
	public Inscripcion getInscripcion(Long id) {
		Assert.notNull(id, "El id no puede ser null");
		Inscripcion inscripcion = null;
		try {
			inscripcion = obtener(id);
		} catch (RuntimeException e) {
			throw new RequestException(Constantes.BAD_REQUEST_COD, NO_EXISTE_INSCRIPCION);
		}
		return inscripcion;
	}

	@Override
	public Inscripcion modificarInscripcion(InscripcionConIdDto inscripcionDto) {
		Assert.notNull(inscripcionDto, "El inscripcionDto no puede ser null");

		Inscripcion inscripcion = getInscripcion(inscripcionDto.getId());

		Usuario usuario = usuarioService.getUsuario(inscripcionDto.getIdUsuario());
		Vacante vacante = vacanteService.getVacante(inscripcionDto.getIdVacante());

		if (!validarRelacionUsuarioVacante(inscripcionDto.getId(), usuario, vacante)) {
			throw new RequestException(Constantes.BAD_REQUEST_COD, YA_EXISTE);
		}

		inscripcion.setUsuario(usuario);
		inscripcion.setVacante(vacante);
		inscripcion.setFechaInscripcion(inscripcionDto.getFechaInscripcion());

		return inscripcion;

	}

	private boolean validarRelacionUsuarioVacante(Long id, Usuario usuario, Vacante vacante) {
		List<Inscripcion> inscripciones = mostrarTodos();

		for (Inscripcion inscripcion : inscripciones) {
			if (!coincideId(inscripcion.getId(), id) && coincideNombre(inscripcion.getUsuario().getNombre(), usuario)
					&& coincideVacante(inscripcion.getVacante().getNombre(), vacante)) {
				return false;
			}
		}
		return true;
	}

	private boolean coincideId(Long idInscripcion, Long idInscripcionRequest) {
		Assert.notNull(idInscripcion, "El idInscripcion no puede ser null");
		return idInscripcion == idInscripcionRequest ? true : false;
	}

	private boolean coincideNombre(String nombre, Usuario usuarioRequest) {
		Assert.notNull(nombre, "El nombre no puede ser null");
		Assert.notNull(usuarioRequest, "El usuarioRequest no puede ser null");
		return nombre.equals(usuarioRequest.getNombre()) ? true : false;
	}

	private boolean coincideVacante(String nombre, Vacante vacanteRequest) {
		Assert.notNull(nombre, "El nombre no puede ser null");
		Assert.notNull(vacanteRequest, "El vacanteRequest no puede ser null");
		return nombre.equals(vacanteRequest.getNombre()) ? true : false;
	}
	
	@Override
	public Inscripcion getNuevaInscripcionValidada(InscripcionDto inscripcionDto) {
		Inscripcion inscripcion = new Inscripcion();

		Usuario usuario = usuarioService.getUsuario(inscripcionDto.getIdUsuario());
		Vacante vacante = vacanteService.getVacante(inscripcionDto.getIdVacante());

		if (!validarRelacionUsuarioVacante(null, usuario, vacante)) {
			throw new RequestException(Constantes.BAD_REQUEST_COD, YA_EXISTE);
		}

		inscripcion.setUsuario(usuario);
		inscripcion.setVacante(vacante);
		inscripcion.setFechaInscripcion(inscripcionDto.getFechaInscripcion());

		return inscripcion;
	}

	@Override
	public EntidadDaoBase<Inscripcion> getRepositorioBase() {
		return inscripcionDao;
	}

	private InscripcionDao getRepository() {
		return ((InscripcionDao) getRepositorioBase());
	}

}
