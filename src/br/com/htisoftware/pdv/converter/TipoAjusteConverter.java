package br.com.htisoftware.pdv.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.htisoftware.pdv.dao.TipoAjusteDAO;
import br.com.htisoftware.pdv.modelo.TipoAjuste;

@SuppressWarnings("rawtypes")
@Named
public class TipoAjusteConverter implements Converter {

	@Inject
	TipoAjusteDAO dao;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String codigoTipoAjuste) {
		int codigo = Integer.parseInt(codigoTipoAjuste);
		TipoAjuste tipoAjuste = dao.findById(codigo);
		return tipoAjuste;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object obj) {
		if (obj == null) {
			return "";
		}
		TipoAjuste tipoAjuste = (TipoAjuste) obj;
		return String.valueOf(tipoAjuste.getCodigo());
	}
}
