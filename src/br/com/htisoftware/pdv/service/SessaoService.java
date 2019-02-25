package br.com.htisoftware.pdv.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.htisoftware.pdv.dao.SessaoDAO;
import br.com.htisoftware.pdv.modelo.Sessao;

public class SessaoService implements Serializable {

	private static final long serialVersionUID = 1369529354221403830L;
	@Inject
	SessaoDAO dao;

	public Sessao sessaoAberta() {
		return dao.sessaoAberta();
	}
}
