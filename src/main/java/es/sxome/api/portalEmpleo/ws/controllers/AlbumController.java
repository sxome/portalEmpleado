package es.sxome.api.portalEmpleo.ws.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.sxome.api.portalEmpleo.exceptions.RequestException;
import es.sxome.api.portalEmpleo.utils.Constantes;
import es.sxome.api.portalEmpleo.ws.entity.Album;
import es.sxome.api.portalEmpleo.ws.service.AlbumService;

@RestController
@RequestMapping("/albumnes")
public class AlbumController {

	@Autowired
	private AlbumService albumService;
	
	@GetMapping
	public ResponseEntity<List<Album>> buscarTodos() {

		List<Album> albumnes = albumService.getAlbums();

		if (albumnes.isEmpty()) {
			throw new RequestException(Constantes.NO_CONTENT_COD, Constantes.LISTA_VACIA);
		}

		return new ResponseEntity<>(albumnes, HttpStatus.OK);
	}
	
}
