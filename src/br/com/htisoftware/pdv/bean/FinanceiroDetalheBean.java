package br.com.htisoftware.pdv.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.com.htisoftware.pdv.modelo.Financeiro;

@Named
@ViewScoped
public class FinanceiroDetalheBean implements Serializable {

	private static final long serialVersionUID = 4021795059988466143L;
	Financeiro financeiro;

	@PostConstruct
	private void init() {
		financeiro = (Financeiro) FacesContext.getCurrentInstance().getExternalContext().getApplicationMap()
				.get("financeiro");
	}

	public Financeiro getFinanceiro() {
		return financeiro;
	}

	public void setFinanceiro(Financeiro financeiro) {
		this.financeiro = financeiro;
	}
}
