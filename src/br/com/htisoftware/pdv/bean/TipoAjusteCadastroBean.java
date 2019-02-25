package br.com.htisoftware.pdv.bean;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.htisoftware.pdv.enums.TipoMovimentacaoEstoque;
import br.com.htisoftware.pdv.modelo.TipoAjuste;
import br.com.htisoftware.pdv.service.TipoAjusteService;

@Named
@ViewScoped
public class TipoAjusteCadastroBean implements Serializable {

	private static final long serialVersionUID = 7468102225278834282L;
	TipoAjuste tipoAjuste;
	@Inject
	private TipoAjusteService tipoAjusteService;

	@PostConstruct
	private void init() {
		tipoAjuste = (TipoAjuste) FacesContext.getCurrentInstance().getExternalContext().getApplicationMap()
				.get("tipoAjuste");
		if (tipoAjuste != null) {
			FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().remove("tipoAjuste");
			return;
		}
		tipoAjuste = new TipoAjuste();
	}

	public List<TipoMovimentacaoEstoque> getTiposMovimentacao() {
		return Arrays.asList(TipoMovimentacaoEstoque.values());
	}

	public void gravar() {
		tipoAjusteService.gravar(tipoAjuste);
		tipoAjuste = new TipoAjuste();
	}

	public TipoAjuste getTipoAjuste() {
		return tipoAjuste;
	}

	public void setTipoAjuste(TipoAjuste tipoAjuste) {
		this.tipoAjuste = tipoAjuste;
	}
}
