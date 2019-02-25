package br.com.htisoftware.pdv.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.htisoftware.pdv.modelo.NCM;
import br.com.htisoftware.pdv.service.NcmService;

@Named
@ViewScoped
public class NcmCadastroBean implements Serializable {

	private static final long serialVersionUID = -8681343079654973707L;
	@Inject
	NCM ncm;
	@Inject
	NcmService ncmService;

	@PostConstruct
	private void init() {
		ncm = (NCM) FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().get("ncm");
		if (ncm != null) {
			FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().remove("ncm");
			return;
		}
		ncm = new NCM();
	}

	public void gravar() {
		ncmService.gravar(ncm);
		ncm = new NCM();
	}

	public NCM getNcm() {
		return ncm;
	}

	public void setNcm(NCM ncm) {
		this.ncm = ncm;
	}
}
