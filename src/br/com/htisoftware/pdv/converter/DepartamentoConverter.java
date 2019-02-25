package br.com.htisoftware.pdv.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.htisoftware.pdv.dao.DepartamentoDAO;
import br.com.htisoftware.pdv.modelo.Departamento;

@SuppressWarnings("rawtypes")
@Named
public class DepartamentoConverter implements Converter {

	@Inject
	DepartamentoDAO dao;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String codigoDepartamento) {
		int codigo;
		try {
			codigo = Integer.parseInt(codigoDepartamento);
		} catch (NumberFormatException e) {
			throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Departamento inválido.", ""));
		}
		return dao.findById(codigo);
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object obj) {
		if (obj == null) {
			return "";
		}
		Departamento departamento = (Departamento) obj;
		return String.valueOf(departamento.getCodigo());
	}
}