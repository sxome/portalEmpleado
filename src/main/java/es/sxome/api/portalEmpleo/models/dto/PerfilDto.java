package es.sxome.api.portalEmpleo.models.dto;

import es.sxome.api.portalEmpleo.models.entity.Perfil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PerfilDto{

	private String perfil;
	
	public static PerfilDto create(Perfil perfil) {
		PerfilDto perfilDto = null;
		
		if(perfil != null) {
			perfilDto = new PerfilDto();
			perfilDto.setPerfil(perfil.getPerfil());
		}
		
		return perfilDto;
	}
}
