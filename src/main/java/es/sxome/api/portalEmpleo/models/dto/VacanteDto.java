package es.sxome.api.portalEmpleo.models.dto;

import java.math.BigDecimal;
import java.util.Date;

import es.sxome.api.portalEmpleo.models.entity.Vacante;
import es.sxome.api.portalEmpleo.models.enums.TipoEstatusVacante;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VacanteDto{

	private String nombre;
	private String descripcion;
	private Date fecha;
	private BigDecimal salario;
	private boolean destacado;
	private String imagen;
	private TipoEstatusVacante estatus;
	private String detalles;
	private Long idCategoria;

	public static VacanteDto create(Vacante vacante) {
		VacanteDto vacanteDto = null;
		
		if(vacante != null) {
			vacanteDto = new VacanteDto();
			vacanteDto.setNombre(vacante.getNombre());
			vacanteDto.setDescripcion(vacante.getDescripcion());
			vacanteDto.setFecha(vacante.getFecha());
			vacanteDto.setSalario(vacante.getSalario());
			vacanteDto.setDestacado(vacante.isDestacado());
			vacanteDto.setImagen(vacante.getImagen());
			vacanteDto.setEstatus(vacante.getEstatus());
			vacanteDto.setDetalles(vacante.getDetalles());
			vacanteDto.setIdCategoria(vacante.getCategoria().getId());
		}
		
		return vacanteDto;
	}

}
