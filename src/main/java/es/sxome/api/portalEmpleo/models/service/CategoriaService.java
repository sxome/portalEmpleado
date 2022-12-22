package es.sxome.api.portalEmpleo.models.service;

import java.util.List;

import es.sxome.api.portalEmpleo.models.entity.Categoria;

public interface CategoriaService extends EntidadService<Categoria>{

	List<Categoria> mostrarTodos();

	boolean existeNombre(String nombre);

	Categoria getCategoria(Long id);

	Categoria modificarCategoria(Categoria categoriaModif);
}
