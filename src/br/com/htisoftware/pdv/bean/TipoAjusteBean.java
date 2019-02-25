package br.com.htisoftware.pdv.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.htisoftware.pdv.modelo.TipoAjuste;
import br.com.htisoftware.pdv.service.TipoAjusteService;

@Named
@ViewScoped
public class TipoAjusteBean implements Serializable {

	private static final long serialVersionUID = -1113588407480438912L;
	List<TipoAjuste> ajustes;
	@Inject
	private TipoAjusteService tipoAjusteService;
	@Inject
	TipoAjuste tipoAjuste;

	@PostConstruct
	private void init() {
		ajustes = tipoAjusteService.findAll();
	}

	public String novo() {
		return "/pages/tipo_ajuste_cadastro?faces-redirect=true";
	}

	public String editar() {
		FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().put("tipoAjuste", tipoAjuste);
		return "/pages/tipo_ajuste_cadastro?faces-redirect=true";
	}

	public List<TipoAjuste> getAjustes() {
		return ajustes;
	}

	public void setAjustes(List<TipoAjuste> ajustes) {
		this.ajustes = ajustes;
	}

	public TipoAjuste getTipoAjuste() {
		return tipoAjuste;
	}

	public void setTipoAjuste(TipoAjuste tipoAjuste) {
		this.tipoAjuste = tipoAjuste;
	}
}
