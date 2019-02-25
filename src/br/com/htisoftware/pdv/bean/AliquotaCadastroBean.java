package br.com.htisoftware.pdv.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.htisoftware.pdv.modelo.Aliquota;
import br.com.htisoftware.pdv.modelo.CST;
import br.com.htisoftware.pdv.service.AliquotaService;
import br.com.htisoftware.pdv.service.CstService;
import br.com.htisoftware.pdv.util.ERPUtils;

@Named
@ViewScoped
public class AliquotaCadastroBean implements Serializable {

	private static final long serialVersionUID = 4149039471953349222L;
	Aliquota aliquota;
	@Inject
	AliquotaService aliquotaService;
	@Inject
	CstService cstService;

	@PostConstruct
	private void init() {
		aliquota = (Aliquota) FacesContext.getCurrentInstance().getExternalContext().getApplicationMap()
				.get("aliquota");
		if (aliquota != null) {
			FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().remove("aliquota");
			return;
		}

		aliquota = new Aliquota();
	}

	public List<CST> getCSTICMS() {
		List<CST> csts = cstService.findAll();
		return ERPUtils.cstICMS(csts);
	}

	public void gravar() {
		aliquotaService.gravar(aliquota);
		aliquota = new Aliquota();
	}

	public Aliquota getAliquota() {
		return aliquota;
	}

	public void setAliquota(Aliquota aliquota) {
		this.aliquota = aliquota;
	}
}
