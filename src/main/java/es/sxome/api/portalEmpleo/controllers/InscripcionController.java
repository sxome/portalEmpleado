package es.sxome.api.portalEmpleo.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.sxome.api.portalEmpleo.exceptions.RequestException;
import es.sxome.api.portalEmpleo.models.dto.InscripcionConIdDto;
import es.sxome.api.portalEmpleo.models.dto.InscripcionDto;
import es.sxome.api.portalEmpleo.models.dto.InscripcionFetchAllDto;
import es.sxome.api.portalEmpleo.models.entity.Inscripcion;
import es.sxome.api.portalEmpleo.models.service.InscripcionService;
import es.sxome.api.portalEmpleo.utils.Constantes;

@RestController
@RequestMapping("/inscripciones")
public class InscripcionController {

	private static final String NO_EXISTE_INSCRIPCION = "No existe inscripcion para el id indicado";
	
	@Autowired
	private InscripcionService inscripcionService;

	@GetMapping("/listar")
	public ResponseEntity<List<InscripcionFetchAllDto>> buscarTodos() {

		List<InscripcionFetchAllDto> inscripcionesDto = new ArrayList<>();
		List<Inscripcion> inscripciones = inscripcionService.mostrarTodos();

		for (Inscripcion inscripcion : inscripciones) {
			inscripcionesDto.add(InscripcionFetchAllDto.create(inscripcion));
		}
		return new ResponseEntity<>(inscripcionesDto, HttpStatus.OK);
	}
	
	@GetMapping("/listar/{id}")
	public ResponseEntity<InscripcionFetchAllDto> mostrar(@PathVariable("id") Long idInscripcion) {

		Inscripcion inscripcion = inscripcionService.getInscripcion(idInscripcion);

		if (inscripcion == null) {
			throw new RequestException(Constantes.BAD_REQUEST_COD, NO_EXISTE_INSCRIPCION);
		}

		return new ResponseEntity<>(InscripcionFetchAllDto.create(inscripcion), HttpStatus.OK);
	}

	@PostMapping("/guardar")
	public ResponseEntity<InscripcionFetchAllDto> guardar(@RequestBody InscripcionDto inscripcionDtoRequest) {

		Inscripcion inscripcion = inscripcionService.getNuevaInscripcionValidada(inscripcionDtoRequest);
		
		InscripcionFetchAllDto inscripcionDto = InscripcionFetchAllDto.create(inscripcionService.guardar(inscripcion));

		return new ResponseEntity<InscripcionFetchAllDto>(inscripcionDto, HttpStatus.CREATED);
	}

	@PutMapping("/modificar")
	public ResponseEntity<InscripcionFetchAllDto> modificar(@RequestBody InscripcionConIdDto inscripcionDtoRequest) {

		Inscripcion inscripcion = inscripcionService.modificarInscripcion(inscripcionDtoRequest);
				
		InscripcionFetchAllDto inscripcionDto = InscripcionFetchAllDto.create(inscripcionService.guardar(inscripcion));
	
		return new ResponseEntity<InscripcionFetchAllDto>(inscripcionDto, HttpStatus.OK);
	}
	
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<Inscripcion> eliminar(@PathVariable("id") Long idInscripcion) {
		
		// Comprobamos si existe la inscripción
		inscripcionService.getInscripcion(idInscripcion);

		return inscripcionService.eliminar(idInscripcion) ? new ResponseEntity<Inscripcion>(HttpStatus.OK)
				: new ResponseEntity<Inscripcion>(HttpStatus.NO_CONTENT);
	}

}