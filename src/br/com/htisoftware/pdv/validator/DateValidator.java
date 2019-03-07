package br.com.htisoftware.pdv.validator;

import java.util.Date;
import java.util.TimeZone;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.DateTimeConverter;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@SuppressWarnings("rawtypes")
@FacesValidator
public class DateValidator implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		DateTimeConverter converter = new DateTimeConverter();
		converter.setPattern("dd/MM/yyyy");
		converter.setTimeZone(TimeZone.getTimeZone("America/Sao_Paulo"));
		Date data = (Date) converter.getAsObject(context, component, (String) value);
		
		if(data == null) {
			throw new ValidatorException(
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro de validação", "Documento inválido"));	
		}
	}
}