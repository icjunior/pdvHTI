package br.com.htisoftware.pdv.service;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.inject.Inject;

import br.com.htisoftware.pdv.dao.TipoAjusteDAO;
import br.com.htisoftware.pdv.modelo.TipoAjuste;
import br.com.htisoftware.pdv.util.PdvUtils;

public class TipoAjusteService implements Serializable {

	private static final long serialVersionUID = 7082380336796497239L;
	@Inject
	TipoAjusteDAO dao;

	public List<TipoAjuste> findAll() {
		return dao.findAll();
	}

	public void gravar(TipoAjuste tipoAjuste) {
		dao.gravar(tipoAjuste);
		PdvUtils.mensagem(FacesMessage.SEVERITY_INFO, "Tipo de ajuste", "Tipo de ajuste cadastrado com sucesso");
	}
}
