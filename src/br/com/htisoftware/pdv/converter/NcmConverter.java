package br.com.htisoftware.pdv.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.htisoftware.pdv.dao.NcmDAO;
import br.com.htisoftware.pdv.modelo.NCM;

@SuppressWarnings("rawtypes")
@Named
public class NcmConverter implements Converter {

	@Inject
	NcmDAO dao;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String codigoNCM) {
		NCM ncm = dao.findById(codigoNCM);
		return ncm;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object obj) {
		if (obj == null) {
			return "";
		}
		NCM ncm = (NCM) obj;
		return String.valueOf(ncm.getCodigo());
	}
}
