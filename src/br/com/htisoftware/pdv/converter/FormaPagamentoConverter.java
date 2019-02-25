package br.com.htisoftware.pdv.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.htisoftware.pdv.dao.FormaPagamentoDAO;
import br.com.htisoftware.pdv.modelo.FormaPagamento;

@SuppressWarnings("rawtypes")
@Named
public class FormaPagamentoConverter implements Converter {

	@Inject
	FormaPagamentoDAO dao;

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String id) {
		int idPagamento;
		try {
			idPagamento = Integer.parseInt(id);
		} catch (NumberFormatException e) {
			throw new ConverterException(
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Forma de pagamento inválida", ""));
		}
		return dao.findById(idPagamento);
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object obj) {
		if (obj == null) {
			return "";
		}
		FormaPagamento formaPagamento = (FormaPagamento) obj;
		return String.valueOf(formaPagamento.getCodigo());
	}
}
