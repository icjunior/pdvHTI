package br.com.htisoftware.pdv.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.htisoftware.pdv.dao.DepartamentoDAO;
import br.com.htisoftware.pdv.modelo.Departamento;

public class DepartamentoService implements Serializable {

	private static final long serialVersionUID = 6532055295477305583L;
	@Inject
	DepartamentoDAO dao;

	public void gravar(Departamento departamento) {
		dao.gravar(departamento);
	}

	public List<Departamento> findAll() {
		return dao.findAll();
	}

}
