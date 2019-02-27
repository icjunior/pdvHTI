package br.com.htisoftware.pdv.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.htisoftware.pdv.dao.CfopDAO;
import br.com.htisoftware.pdv.modelo.CFOP;

@SuppressWarnings("rawtypes")
@Named
public class CfopConverter implements Converter {

	@Inject
	CfopDAO dao;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String codigo) {
		CFOP cfop = dao.findById(codigo);
		return cfop;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object obj) {
		if (obj == null) {
			return "";
		}
		CFOP cfop = (CFOP) obj;
		return String.valueOf(cfop.getCfop());
	}
}
