package br.com.htisoftware.pdv.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.htisoftware.pdv.dao.CstDAO;
import br.com.htisoftware.pdv.modelo.CST;

@SuppressWarnings("rawtypes")
@Named
public class CstConverter implements Converter {

	@Inject
	CstDAO dao;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String codigoCST) {
		int codigo = Integer.parseInt(codigoCST);
		CST cst = dao.findById(codigo);
		return cst;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object obj) {
		if (obj == null) {
			return "";
		}
		CST cst = (CST) obj;
		return String.valueOf(cst.getCodigo());
	}
}
