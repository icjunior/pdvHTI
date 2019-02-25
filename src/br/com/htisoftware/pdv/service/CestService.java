package br.com.htisoftware.pdv.service;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.inject.Inject;

import br.com.htisoftware.pdv.dao.CestDAO;
import br.com.htisoftware.pdv.dto.CestDTO;
import br.com.htisoftware.pdv.modelo.CEST;
import br.com.htisoftware.pdv.util.PdvUtils;

public class CestService implements Serializable {

	private static final long serialVersionUID = 7615151068760544679L;
	@Inject
	CestDAO dao;

	public List<CEST> findAll() {
		return dao.findAll();
	}

	public List<CEST> buscar(CestDTO cestDTO) {
		return dao.buscar(cestDTO);
	}

	public void gravar(CEST cest) {
		dao.gravar(cest);
		PdvUtils.mensagem(FacesMessage.SEVERITY_INFO, "Cadastro de CEST", "CEST gravado com sucesso");
	}
}