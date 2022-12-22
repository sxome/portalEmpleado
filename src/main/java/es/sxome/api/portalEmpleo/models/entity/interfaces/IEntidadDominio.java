package es.sxome.api.portalEmpleo.models.entity.interfaces;

import java.io.Serializable;


public interface IEntidadDominio<ID extends Serializable> extends Serializable{

	ID getId();

	void setId(ID id);

}
