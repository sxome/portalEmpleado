package es.sxome.api.portalEmpleo.models.dto;

import java.util.Date;

import es.sxome.api.portalEmpleo.models.entity.Inscripcion;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InscripcionFetchAllDto{

	private VacanteDto vacanteDto;
	private UsuarioDto usuarioDto;
	private Date fechaInscripcion;

	public static InscripcionFetchAllDto create(Inscripcion inscripcion) {
		InscripcionFetchAllDto inscripcionDto = null;
		
		if(inscripcion != null) {
			inscripcionDto = new InscripcionFetchAllDto();
			inscripcionDto.setFechaInscripcion(inscripcion.getFechaInscripcion());
			inscripcionDto.setUsuarioDto(UsuarioDto.create(inscripcion.getUsuario()));
			inscripcionDto.setVacanteDto(VacanteDto.create(inscripcion.getVacante()));
		}
		
		return inscripcionDto;
	}

}
