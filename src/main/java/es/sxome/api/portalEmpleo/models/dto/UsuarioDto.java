package es.sxome.api.portalEmpleo.models.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import es.sxome.api.portalEmpleo.models.entity.Perfil;
import es.sxome.api.portalEmpleo.models.entity.Usuario;
import es.sxome.api.portalEmpleo.models.enums.TipoEstatusUsuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDto{

	private String username;
	private String nombre;
	private String email;
	private String password;
	private TipoEstatusUsuario estatus;
	private Date fechaRegistro;
	private Set<PerfilDto> perfiles;

	public static UsuarioDto create(Usuario usuario) {
		UsuarioDto usuarioDto = null;
		
		if(usuario != null) {
			usuarioDto = new UsuarioDto();
			usuarioDto.setUsername(usuario.getUsername());
			usuarioDto.setNombre(usuario.getNombre());
			usuarioDto.setEmail(usuario.getEmail());
			usuarioDto.setPassword(usuario.getPassword());
			usuarioDto.setEstatus(usuario.getEstatus());
			usuarioDto.setFechaRegistro(usuario.getFechaRegistro());
			usuarioDto.setPerfiles(addPerfilesDto(usuario.getPerfiles()));
		}
		
		return usuarioDto;
	}
	
	private static Set<PerfilDto> addPerfilesDto(Set<Perfil> perfiles){
		
		Set<PerfilDto> perfilesDto = new HashSet<>();
		if(perfiles != null) {
			for (Perfil perfil : perfiles) {
				perfilesDto.add(PerfilDto.create(perfil));
			}
		}
		
		return perfilesDto;
	}
}
