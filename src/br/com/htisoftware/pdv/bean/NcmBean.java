package br.com.htisoftware.pdv.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.htisoftware.pdv.modelo.NCM;
import br.com.htisoftware.pdv.service.NcmService;

@Named
@ViewScoped
public class NcmBean implements Serializable {

	private static final long serialVersionUID = 7517588060989160880L;
	@Inject
	NCM ncm;
	List<NCM> ncms;
	@Inject
	NcmService ncmService;
	String codNcm;

	public String novo() {
		return "/pages/ncm_cadastro?faces-redirect=true";
	}

	public String editar() {
		FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().put("ncm", ncm);
		return "/pages/ncm_cadastro?faces-redirect=true";
	}

	public void buscar() {
		ncms = ncmService.buscar(codNcm);
	}

	public NCM getNcm() {
		return ncm;
	}

	public void setNcm(NCM ncm) {
		this.ncm = ncm;
	}

	public List<NCM> getNcms() {
		return ncms;
	}

	public void setNcms(List<NCM> ncms) {
		this.ncms = ncms;
	}

	public String getCodNcm() {
		return codNcm;
	}

	public void setCodNcm(String codNcm) {
		this.codNcm = codNcm;
	}
}
