package br.com.htisoftware.pdv.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import br.com.caelum.stella.validation.CNPJValidator;
import br.com.caelum.stella.validation.CPFValidator;
import br.com.caelum.stella.validation.InvalidStateException;

@SuppressWarnings("rawtypes")
@FacesValidator
public class NFPValidator implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		String nfp = value.toString();
		CPFValidator cpfValidator = new CPFValidator();
		CNPJValidator cnpjValidator = new CNPJValidator();

		try {
			if (nfp.length() > 11) {
				cnpjValidator.assertValid(nfp);
			}
			cpfValidator.assertValid(nfp);
		} catch (InvalidStateException ex) {
			throw new ValidatorException(
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro de validação", "Documento inválido"));
		}
	}
}