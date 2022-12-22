package es.sxome.api.portalEmpleo.models.service;

import java.util.List;
import java.util.Set;

import es.sxome.api.portalEmpleo.models.entity.Perfil;

public interface PerfilService extends EntidadService<Perfil>{

	List<Perfil> mostrarTodos();

	boolean existePerfil(String perfil);

	boolean existePerfil(Set<Perfil> perfilesUsuario);

	Perfil buscaPerfilPorNombrePerfil(String perfil);
	
	boolean isNombrePerfilValido(Perfil perfilRequest);

	boolean existePerfil(Long id);

	Perfil getPerfil(Long idPerfil);

}
