package br.com.htisoftware.pdv.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.htisoftware.pdv.dao.AliquotaDAO;
import br.com.htisoftware.pdv.modelo.Aliquota;

@SuppressWarnings("rawtypes")
@Named
public class AliquotaConverter implements Converter {

	@Inject
	AliquotaDAO dao;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String codigoAliquota) {
		int codigo = Integer.parseInt(codigoAliquota);
		Aliquota aliquota = dao.findById(codigo);
		return aliquota;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object obj) {
		if (obj == null) {
			return "";
		}
		Aliquota aliquota = (Aliquota) obj;
		return String.valueOf(aliquota.getCodigo());
	}
}
