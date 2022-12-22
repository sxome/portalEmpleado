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
import es.sxome.api.portalEmpleo.models.dto.PerfilDto;
import es.sxome.api.portalEmpleo.models.entity.Perfil;
import es.sxome.api.portalEmpleo.models.service.PerfilService;
import es.sxome.api.portalEmpleo.utils.Constantes;

@RestController
@RequestMapping("/perfiles")
public class PerfilController {

	private static final String NO_EXISTE_PERFIL = "No existe perfil para el id indicado";
	private static final String PERFIL_REPETIDO = "Ya existe un perfil con ese nombre";
	private static final String NOMBRE_PERFIL_REPETIDO = "Ya existe un perfil con ese nombre";
	private static final String NOMBRE_PERFIL_INVALIDO = "Nombre de perfil invalido";
	
	@Autowired
	private PerfilService perfilService;

	@GetMapping("/listar")
	public ResponseEntity<List<PerfilDto>> buscarTodos() {

		List<PerfilDto> perfilesDto = new ArrayList<>();
		List<Perfil> perfiles = perfilService.mostrarTodos();

		if (perfiles.isEmpty()) {
			throw new RequestException(Constantes.NO_CONTENT_COD, Constantes.LISTA_VACIA);
		}

		for (Perfil perfil : perfiles) {
			perfilesDto.add(PerfilDto.create(perfil));
		}
		return new ResponseEntity<>(perfilesDto, HttpStatus.OK);
	}
	
	@GetMapping("/listar/{id}")
	public ResponseEntity<PerfilDto> mostrar(@PathVariable("id") Long idPerfil) {

		Perfil perfil = perfilService.getPerfil(idPerfil);

		if (perfil == null) {
			throw new RequestException(Constantes.BAD_REQUEST_COD, NO_EXISTE_PERFIL);
		}

		return new ResponseEntity<>(PerfilDto.create(perfil), HttpStatus.OK);
	}

	@PostMapping("/guardar")
	public ResponseEntity<PerfilDto> guardar(@RequestBody Perfil perfil) {

		if (perfilService.existePerfil(perfil.getPerfil())) {
			throw new RequestException(Constantes.BAD_REQUEST_COD, PERFIL_REPETIDO);
		}

		PerfilDto perfilDto = PerfilDto.create(perfilService.guardar(perfil));

		return new ResponseEntity<PerfilDto>(perfilDto, HttpStatus.CREATED);
	}

	@PutMapping("/modificar")
	public ResponseEntity<Perfil> modificar(@RequestBody Perfil perfil) {
		
		if(perfil.getPerfil().isBlank()) {
			throw new RequestException(Constantes.BAD_REQUEST_COD, NOMBRE_PERFIL_INVALIDO);
		}
		if(!perfilService.existePerfil(perfil.getId())) {
			throw new RequestException(Constantes.BAD_REQUEST_COD, NO_EXISTE_PERFIL);
		}
		if(!perfilService.isNombrePerfilValido(perfil)) {
			throw new RequestException(Constantes.BAD_REQUEST_COD, NOMBRE_PERFIL_REPETIDO);
		}

		perfilService.guardar(perfil);
	
		return new ResponseEntity<Perfil>(perfil, HttpStatus.OK);
	}

	
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<Perfil> eliminar(@PathVariable("id") Long idPerfil) {
		
		// Comprobamos si existe el perfil
		perfilService.getPerfil(idPerfil);

		return perfilService.eliminar(idPerfil) ? new ResponseEntity<Perfil>(HttpStatus.OK)
				: new ResponseEntity<Perfil>(HttpStatus.NO_CONTENT);
	}
}
