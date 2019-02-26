package br.com.htisoftware.pdv.service;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.inject.Inject;

import br.com.htisoftware.pdv.dao.NotaDAO;
import br.com.htisoftware.pdv.modelo.NotaCabecalho;
import br.com.htisoftware.pdv.util.PdvUtils;

public class NotaService implements Serializable {

	private static final long serialVersionUID = -925297650249354734L;
	@Inject
	NotaDAO dao;

	public void gravar(NotaCabecalho notaCabecalho) {
		dao.gravar(notaCabecalho);
		PdvUtils.mensagem(FacesMessage.SEVERITY_INFO, "Nota fiscal", "Nota fiscal lançada com sucesso");
	}
}
