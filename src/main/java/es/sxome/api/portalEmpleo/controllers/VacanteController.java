package es.sxome.api.portalEmpleo.controllers;

import java.math.BigDecimal;
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
import es.sxome.api.portalEmpleo.models.dto.VacanteConIdDto;
import es.sxome.api.portalEmpleo.models.dto.VacanteDto;
import es.sxome.api.portalEmpleo.models.entity.Vacante;
import es.sxome.api.portalEmpleo.models.enums.TipoEstatusVacante;
import es.sxome.api.portalEmpleo.models.service.VacanteService;
import es.sxome.api.portalEmpleo.utils.Constantes;

@RestController
@RequestMapping("/vacantes")
public class VacanteController {

	private static final String NO_EXISTE_VACANTE = "No existe vacante para el id indicado";
	
	@Autowired
	private VacanteService vacanteService;


	@GetMapping("/listar")
	public ResponseEntity<List<VacanteDto>> buscarTodos() {

		List<VacanteDto> vacantesDto = new ArrayList<>();
		List<Vacante> vacantes = vacanteService.mostrarTodos();

		for (Vacante vacante : vacantes) {
			vacantesDto.add(VacanteDto.create(vacante));
		}
		return new ResponseEntity<>(vacantesDto, HttpStatus.OK);
	}

	@GetMapping("/listar/{id}")
	public ResponseEntity<VacanteDto> mostrar(@PathVariable("id") Long idVacante) {

		Vacante vacante = vacanteService.obtenerVacanteFetchAll(idVacante);

		if (vacante == null) {
			throw new RequestException(Constantes.BAD_REQUEST_COD, NO_EXISTE_VACANTE);
		}

		return new ResponseEntity<>(VacanteDto.create(vacante), HttpStatus.OK);
	}

	@PostMapping("/guardar")
	public ResponseEntity<VacanteDto> guardar(@RequestBody VacanteDto vacanteDto) {

		// Al guardar la vacante se comprueba si la categoria que se recibe existe y
		// en caso negativo devolvemos un RequestException
		vacanteService.guardaVacante(vacanteDto);

		return new ResponseEntity<VacanteDto>(vacanteDto, HttpStatus.CREATED);
	}

	@PutMapping("/modificar")
	public ResponseEntity<VacanteDto> modificar(@RequestBody VacanteConIdDto vacanteDto) {


		// Al guardar la vacante se comprueba si la vacante que se recibe existe y
		// en caso negativo devolvemos un RequestException
		Vacante vacante = vacanteService.modificarVacante(vacanteDto);

		return new ResponseEntity<VacanteDto>(VacanteDto.create(vacante), HttpStatus.OK);
	}
	
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<Vacante> eliminar(@PathVariable("id") Long idVacante) {
		
		// Comprobamos si existe la vacante
		vacanteService.getVacante(idVacante);

		return vacanteService.eliminar(idVacante) ? new ResponseEntity<Vacante>(HttpStatus.OK)
				: new ResponseEntity<Vacante>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/filtro1")
	public ResponseEntity<List<VacanteDto>> getVacantePorCategoriaYSalarioMayorQue(
			@RequestParam(name = "idCategoria") Long idCategoria, @RequestParam("salario") BigDecimal salario) {

		List<VacanteDto> vacanteDto = new ArrayList<>();
		List<Vacante> vacantes = vacanteService.getVacantePorCategoriaYSalarioMayorQue(idCategoria, salario);

		for (Vacante vacante : vacantes) {
			vacanteDto.add(VacanteDto.create(vacante));
		}
		return new ResponseEntity<>(vacanteDto, HttpStatus.OK);

	}

	@GetMapping("/filtro2")
	public ResponseEntity<List<VacanteDto>> getVacantePorCategoriaYSalarioMayorQue(
			@RequestParam(name = "estatus") TipoEstatusVacante estatus, @RequestParam("salario") BigDecimal salario,
			@RequestParam(name = "fecha1") Date fecha1, @RequestParam("fecha2") Date fecha2) {

		List<VacanteDto> vacanteDto = new ArrayList<>();
		List<Vacante> vacantes = vacanteService.findByEstatusAndSalarioAndFechaBetween(estatus, salario, fecha1, fecha2);

		for (Vacante vacante : vacantes) {
			vacanteDto.add(VacanteDto.create(vacante));
		}
		return new ResponseEntity<>(vacanteDto, HttpStatus.OK);

	}
	
	
}