package br.com.htisoftware.pdv.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.htisoftware.pdv.modelo.CEST;
import br.com.htisoftware.pdv.service.CestService;

@Named
@ViewScoped
public class CestCadastroBean implements Serializable {

	private static final long serialVersionUID = -585856026892800274L;

	CEST cest;
	@Inject
	CestService cestService;

	@PostConstruct
	private void init() {
		cest = (CEST) FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().get("cest");
		if (cest != null) {
			FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().remove("cest");
			return;
		}

		cest = new CEST();
	}

	public void gravar() {
		cestService.gravar(cest);
		cest = new CEST();
	}

	public CEST getCest() {
		return cest;
	}

	public void setCest(CEST cest) {
		this.cest = cest;
	}
}
