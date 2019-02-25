package br.com.htisoftware.pdv.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.htisoftware.pdv.dto.CestDTO;
import br.com.htisoftware.pdv.modelo.CEST;
import br.com.htisoftware.pdv.service.CestService;

@Named
@ViewScoped
public class CestBean implements Serializable {

	private static final long serialVersionUID = 4719860260466913568L;
	@Inject
	CEST cest;
	List<CEST> cests;
	@Inject
	CestService cestService;
	@Inject
	CestDTO cestDTO;

	public String novo() {
		return "/pages/cest_cadastro?faces-redirect=true";
	}

	public String editar() {
		FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().put("cest", cest);
		return "/pages/cest_cadastro?faces-redirect=true";
	}

	public void buscar() {
		cests = cestService.buscar(cestDTO);
	}

	public CEST getCest() {
		return cest;
	}

	public void setCest(CEST cest) {
		this.cest = cest;
	}

	public List<CEST> getCests() {
		return cests;
	}

	public void setCests(List<CEST> cests) {
		this.cests = cests;
	}

	public CestDTO getCestDTO() {
		return cestDTO;
	}

	public void setCestDTO(CestDTO cestDTO) {
		this.cestDTO = cestDTO;
	}
}
