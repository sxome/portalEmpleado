package es.sxome.api.portalEmpleo.models.dto;

import es.sxome.api.portalEmpleo.models.entity.Categoria;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaDto{

	private String nombre;
	private String descripcion;

	public static CategoriaDto create(Categoria categoria) {
		
		CategoriaDto categoriaDto = null;
		
		if(categoria != null) {
			categoriaDto  = new CategoriaDto();
			categoriaDto.setNombre(categoria.getNombre());
			categoriaDto.setDescripcion(categoria.getDescripcion());
		}
		
		return categoriaDto ;
	}
}
