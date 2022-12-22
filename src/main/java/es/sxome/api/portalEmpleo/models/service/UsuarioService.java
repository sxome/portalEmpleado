package es.sxome.api.portalEmpleo.models.service;

import java.util.Date;
import java.util.List;

import es.sxome.api.portalEmpleo.models.dto.UsuarioDto;
import es.sxome.api.portalEmpleo.models.entity.Usuario;
import es.sxome.api.portalEmpleo.models.enums.TipoEstatusUsuario;

public interface UsuarioService extends EntidadService<Usuario>{

	List<Usuario> mostrarTodos();
	
	Usuario obtenerUsuarioFetchAll(Long id);
	
	List<Usuario> obtenerUsuariosPorEstatusYFechaRegistroMayorQue(TipoEstatusUsuario estatus, Date fecha);

	Usuario getUsuario(String username);

	void guardaUsuario(Long id, UsuarioDto usuarioDto);

	boolean existeUsuario(Long id);

	void validaUsername(String username);

	Usuario devuelveUsuarioValidado(String username);

	Usuario getUsuario(Long id);

}
