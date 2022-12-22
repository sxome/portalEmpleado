package es.sxome.api.portalEmpleo.controllers;

import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.sxome.api.portalEmpleo.exceptions.RequestException;
import es.sxome.api.portalEmpleo.models.dto.UsuarioDto;
import es.sxome.api.portalEmpleo.models.entity.Usuario;
import es.sxome.api.portalEmpleo.models.enums.TipoEstatusUsuario;
import es.sxome.api.portalEmpleo.models.service.UsuarioService;
import es.sxome.api.portalEmpleo.utils.Constantes;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	private static final String NO_EXISTE_USUARIO = "No existe usuario para el id indicado";

	@Autowired
	private UsuarioService usuarioService;

	@GetMapping("/listar")
	public ResponseEntity<List<UsuarioDto>> buscarTodos() {

		List<UsuarioDto> usuariosDto = new ArrayList<>();
		List<Usuario> usuarios = usuarioService.mostrarTodos();

		for (Usuario usuario : usuarios) {
			usuariosDto.add(UsuarioDto.create(usuario));
		}
		return new ResponseEntity<>(usuariosDto, HttpStatus.OK);
	}

	@GetMapping("/listar/{id}")
	public ResponseEntity<UsuarioDto> mostrar(@PathVariable("id") Long idUsuario) {

		Usuario usuario = usuarioService.obtenerUsuarioFetchAll(idUsuario);
		
		if(usuario == null) {
			throw new RequestException(Constantes.BAD_REQUEST_COD, NO_EXISTE_USUARIO);
		}

		return new ResponseEntity<>(UsuarioDto.create(usuario), HttpStatus.OK);
	}

	@PostMapping("/guardar")
	public ResponseEntity<UsuarioDto> guardar(@RequestBody UsuarioDto usuarioDto) {

		// Comprobamos si ya existe un Usuario con el mismo username que se está
		// intentando guardar
		usuarioService.validaUsername(usuarioDto.getUsername());

		// Al guardar el usuario se comprueba si los perfiles que se reciben existen y
		// en caso negativo devolvemos un RequestException
		usuarioService.guardaUsuario(null, usuarioDto);

		return new ResponseEntity<UsuarioDto>(usuarioDto, HttpStatus.CREATED);
	}

	/**
	 * Se modifican los usuarios utilizando como identificador en el JSON del RequestBody 
	 * el username.
	 * 
	 * @param UsuarioDto
	 * @return ResponseEntity<UsuarioDto>
	 */
	@PutMapping("/modificar")
	public ResponseEntity<UsuarioDto> modificar(@RequestBody UsuarioDto usuarioDto) {

		// Recuperamos de base de datos el usuario a partir del username que recibimos
		// en el RequestBody
		Usuario usuario = usuarioService.devuelveUsuarioValidado(usuarioDto.getUsername());

		// Al guardar el usuario se comprueba si los perfiles que se reciben existen y
		// en caso negativo devolvemos un RequestException
		usuarioService.guardaUsuario(usuario.getId(), usuarioDto);

		return new ResponseEntity<UsuarioDto>(usuarioDto, HttpStatus.OK);
	}
	
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<Usuario> eliminar(@PathVariable("id") Long idUsuario) {
		
		// Comprobamos si existe el usuario
		usuarioService.getUsuario(idUsuario);

		return usuarioService.eliminar(idUsuario) ? new ResponseEntity<Usuario>(HttpStatus.OK)
				: new ResponseEntity<Usuario>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/buscar")
	public ResponseEntity<List<UsuarioDto>> mostrarPorEstatusYFechaMayorQue(
			@RequestParam(name = "tipo") TipoEstatusUsuario tipo, @RequestParam("fechaRegistro") Date fechaRegistro) {

		List<UsuarioDto> usuariosDto = new ArrayList<>();
		List<Usuario> usuarios = usuarioService.obtenerUsuariosPorEstatusYFechaRegistroMayorQue(tipo, fechaRegistro);

		for (Usuario usuario : usuarios) {
			usuariosDto.add(UsuarioDto.create(usuario));
		}
		return new ResponseEntity<>(usuariosDto, HttpStatus.OK);

	}

}
