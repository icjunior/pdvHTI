package br.com.htisoftware.pdv.service;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.inject.Inject;

import br.com.htisoftware.pdv.dao.NcmDAO;
import br.com.htisoftware.pdv.modelo.NCM;
import br.com.htisoftware.pdv.util.PdvUtils;

public class NcmService implements Serializable {

	private static final long serialVersionUID = 659456224312824622L;
	@Inject
	NcmDAO dao;

	public List<NCM> findAll() {
		return dao.findAll();
	}

	public List<NCM> buscar(String ncm) {
		return dao.buscar(ncm);
	}

	public void gravar(NCM ncm) {
		dao.gravar(ncm);
		PdvUtils.mensagem(FacesMessage.SEVERITY_INFO, "Cadastro de NCM", "NCM gravado com sucesso");
	}
}
