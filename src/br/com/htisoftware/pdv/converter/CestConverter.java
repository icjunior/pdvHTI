package br.com.htisoftware.pdv.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.htisoftware.pdv.dao.CestDAO;
import br.com.htisoftware.pdv.modelo.CEST;

@SuppressWarnings("rawtypes")
@Named
public class CestConverter implements Converter {

	@Inject
	CestDAO dao;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String codigoCEST) {
		CEST cest = dao.findById(codigoCEST);
		return cest;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object obj) {
		if (obj == null) {
			return "";
		}
		CEST cest = (CEST) obj;
		return String.valueOf(cest.getCodigo());
	}
}
