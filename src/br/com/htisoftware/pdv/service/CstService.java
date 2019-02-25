package br.com.htisoftware.pdv.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.htisoftware.pdv.dao.CstDAO;
import br.com.htisoftware.pdv.modelo.CST;

public class CstService implements Serializable {

	private static final long serialVersionUID = -9190193835647554944L;
	@Inject
	CstDAO dao;

	public List<CST> findAll() {
		return dao.findAll();
	}
}
