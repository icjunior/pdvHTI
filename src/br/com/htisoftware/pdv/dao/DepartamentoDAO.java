package br.com.htisoftware.pdv.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.htisoftware.pdv.modelo.Departamento;

public class DepartamentoDAO {

	@Inject
	EntityManager em;

	public void gravar(Departamento departamento) {
		em.getTransaction().begin();
		em.merge(departamento);
		em.getTransaction().commit();
	}

	public List<Departamento> findAll() {
		String jpql = "SELECT d FROM Departamento d ORDER BY d.codigo";
		TypedQuery<Departamento> query = em.createQuery(jpql, Departamento.class);
		return query.getResultList();
	}

	public Departamento findById(int codigo) {
		return em.find(Departamento.class, codigo);
	}
}