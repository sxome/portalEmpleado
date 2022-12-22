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
public class InscripcionDto{

	private Long idVacante;
	private Long idUsuario;
	private Date fechaInscripcion;

	public static InscripcionDto create(Inscripcion inscripcion) {
		InscripcionDto inscripcionDto = null;
		
		if(inscripcion != null) {
			inscripcionDto = new InscripcionDto();
			inscripcionDto.setFechaInscripcion(inscripcion.getFechaInscripcion());
			inscripcionDto.setIdUsuario(inscripcion.getUsuario() != null ? inscripcion.getUsuario().getId() : null);
			inscripcionDto.setIdVacante(inscripcion.getVacante() != null ? inscripcion.getVacante().getId() : null);
		}
		
		return inscripcionDto;
	}

}
