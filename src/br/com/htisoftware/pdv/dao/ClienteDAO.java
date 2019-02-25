package br.com.htisoftware.pdv.dao;

import java.util.List;

import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.htisoftware.pdv.modelo.Cliente;
import br.com.htisoftware.pdv.modelo.Endereco;
import br.com.htisoftware.pdv.util.PdvUtils;

public class ClienteDAO {

	@Inject
	EntityManager em;

	public List<Cliente> pesquisar(String nome, String cnpj, String fantasia) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Cliente> query = builder.createQuery(Cliente.class);
		Root<Cliente> from = query.from(Cliente.class);
		Predicate predicate = builder.and();

		if (nome != "") {
			predicate = builder.and(predicate,
					builder.like(builder.lower(from.get("nome")), "%" + nome.toLowerCase() + "%"));
		}

		if (cnpj != "") {
			predicate = builder.and(predicate, builder.equal(from.get("cpfCnpj"), cnpj));
		}

		if (fantasia != "") {
			predicate = builder.and(predicate,
					builder.like(builder.lower(from.get("apelido")), "%" + fantasia.toLowerCase() + "%"));
		}

		TypedQuery<Cliente> typedQuery = em
				.createQuery(query.select(from).where(predicate).orderBy(builder.asc(from.get("nome"))));
		return typedQuery.getResultList();
	}

	public void gravar(Cliente cliente) {
		try {
			List<Endereco> enderecos = cliente.getEnderecos();
			em.getTransaction().begin();
			em.merge(cliente);
			enderecos.forEach(e -> {
				e.setCliente(cliente);
				em.merge(e);
			});
			em.getTransaction().commit();
		} catch (Exception e) {
			PdvUtils.mensagem(FacesMessage.SEVERITY_ERROR, "Tentativa de inclusão de registros em duplicidade",
					"O cliente " + cliente.getCpfCnpj() + " já existe no sistema.");
			throw new FacesException("O cliente " + cliente.getCpfCnpj() + " já existe no sistema.");
		}

	}
}