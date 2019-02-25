package br.com.htisoftware.pdv.service;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.inject.Inject;

import br.com.htisoftware.pdv.dao.AliquotaDAO;
import br.com.htisoftware.pdv.modelo.Aliquota;
import br.com.htisoftware.pdv.util.PdvUtils;

public class AliquotaService implements Serializable {

	private static final long serialVersionUID = -3500343011499156495L;
	@Inject
	AliquotaDAO dao;

	public List<Aliquota> findAll() {
		return dao.findAll();
	}

	public void gravar(Aliquota aliquota) {
		dao.gravar(aliquota);
		PdvUtils.mensagem(FacesMessage.SEVERITY_INFO, "Cadastro de ICMS", "ICMS gravado com sucesso");
	}
}
