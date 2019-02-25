package br.com.htisoftware.pdv.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.htisoftware.pdv.modelo.Aliquota;
import br.com.htisoftware.pdv.service.AliquotaService;

@ViewScoped
@Named
public class AliquotaBean implements Serializable {

	private static final long serialVersionUID = 5917387059303057916L;
	private List<Aliquota> aliquotas;
	@Inject
	AliquotaService aliquotaService;
	@Inject
	Aliquota aliquota;

	@PostConstruct
	private void init() {
		aliquotas = aliquotaService.findAll();
	}

	public String novo() {
		System.out.println("Teste");
		return "/pages/aliquota_cadastro?faces-redirect=true";
	}

	public String editar() {
		FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().put("aliquota", aliquota);
		return "/pages/aliquota_cadastro?faces-redirect=true";
	}

	public List<Aliquota> getAliquotas() {
		return aliquotas;
	}

	public void setAliquotas(List<Aliquota> aliquotas) {
		this.aliquotas = aliquotas;
	}

	public Aliquota getAliquota() {
		return aliquota;
	}

	public void setAliquota(Aliquota aliquota) {
		this.aliquota = aliquota;
	}
}
