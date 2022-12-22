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
import es.sxome.api.portalEmpleo.models.dto.CategoriaDto;
import es.sxome.api.portalEmpleo.models.entity.Categoria;
import es.sxome.api.portalEmpleo.models.service.CategoriaService;
import es.sxome.api.portalEmpleo.utils.Constantes;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

	private static final String NO_EXISTE_CATEGORIA = "No existe categoria para el id indicado";

	@Autowired
	private CategoriaService categoriaService;

	@GetMapping("/listar")
	public ResponseEntity<List<CategoriaDto>> buscarTodos() {

		List<CategoriaDto> catgoriasDto = new ArrayList<>();
		List<Categoria> categorias = categoriaService.mostrarTodos();

		if (categorias.isEmpty()) {
			throw new RequestException(Constantes.NO_CONTENT_COD, Constantes.LISTA_VACIA);
		}

		for (Categoria categoria : categorias) {
			catgoriasDto.add(CategoriaDto.create(categoria));
		}
		return new ResponseEntity<>(catgoriasDto, HttpStatus.OK);
	}

	@GetMapping("/listar/{id}")
	public ResponseEntity<CategoriaDto> mostrar(@PathVariable("id") Long idCategoria) {
		
		Categoria categoria = null;
		try {
			categoria = categoriaService.obtener(idCategoria);
		} catch (RuntimeException e) {
			throw new RequestException(Constantes.BAD_REQUEST_COD, NO_EXISTE_CATEGORIA);
		}

		return new ResponseEntity<>(CategoriaDto.create(categoria), HttpStatus.OK);
	}

	@PostMapping("/guardar")
	public ResponseEntity<CategoriaDto> guardar(@RequestBody Categoria categoria) {

		CategoriaDto categoriaDto = CategoriaDto.create(categoriaService.guardar(categoria));

		return new ResponseEntity<CategoriaDto>(categoriaDto, HttpStatus.CREATED);
	}

	@PutMapping("/modificar")
	public ResponseEntity<CategoriaDto> modificar(@RequestBody Categoria categoriaRequest) {

		// Al guardar la categoria se comprueba si la categoria que se recibe existe y
		// en caso negativo devolvemos un RequestException
		Categoria categoria = categoriaService.modificarCategoria(categoriaRequest);

		return new ResponseEntity<CategoriaDto>(CategoriaDto.create(categoria), HttpStatus.OK);
	}
	
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<Categoria> eliminar(@PathVariable("id") Long idCategoria) {
		
		// Comprobamos si existe el categoria
		categoriaService.getCategoria(idCategoria);

		return categoriaService.eliminar(idCategoria) ? new ResponseEntity<Categoria>(HttpStatus.OK)
				: new ResponseEntity<Categoria>(HttpStatus.NO_CONTENT);
	}
}
