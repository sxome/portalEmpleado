package es.sxome.api.portalEmpleo.models.service;

import java.util.List;

import es.sxome.api.portalEmpleo.models.dto.InscripcionConIdDto;
import es.sxome.api.portalEmpleo.models.dto.InscripcionDto;
import es.sxome.api.portalEmpleo.models.entity.Inscripcion;

public interface InscripcionService extends EntidadService<Inscripcion>{

	List<Inscripcion> mostrarTodos();

	Inscripcion getInscripcion(Long id);

	Inscripcion modificarInscripcion(InscripcionConIdDto inscripcionDto);

	Inscripcion getNuevaInscripcionValidada(InscripcionDto inscripcionDto);
	
}
