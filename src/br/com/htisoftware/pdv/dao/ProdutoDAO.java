package br.com.htisoftware.pdv.dao;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.htisoftware.pdv.dto.RelatorioProdutoEstoqueDTO;
import br.com.htisoftware.pdv.modelo.Produto;
import br.com.htisoftware.pdv.modelo.ProdutoAjusteEstoque;
import br.com.htisoftware.pdv.modelo.TipoAjuste;
import br.com.htisoftware.pdv.util.DataUtils;
import br.com.htisoftware.pdv.util.ERPUtils;
import br.com.htisoftware.pdv.util.PdvUtils;

public class ProdutoDAO {

	@Inject
	EntityManager em;

	public Produto materialExiste(Produto produto) {
		String jpql = "SELECT p FROM Produto p WHERE p.ean = :pCodigo";
		TypedQuery<Produto> query = em.createQuery(jpql, Produto.class);
		query.setParameter("pCodigo", produto.getEan());
		try {
			Produto produtoEncontrado = query.getSingleResult();
			return produtoEncontrado;
		} catch (javax.persistence.NoResultException e) {
			return null;
		}
	}

	public List<Produto> findByName(String descricao) {
		String jpql = "SELECT p FROM Produto p WHERE UPPER(p.descricao) LIKE UPPER(:pDescricao)";
		TypedQuery<Produto> query = em.createQuery(jpql, Produto.class);
		query.setParameter("pDescricao", "%" + descricao + "%");
		return query.getResultList();
	}

	public List<Produto> findAll() {
		String jpql = "SELECT p FROM Produto p ORDER BY p.codigo";
		TypedQuery<Produto> query = em.createQuery(jpql, Produto.class);
		return query.getResultList();
	}

	public List<Produto> find(String ean, String descricao) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Produto> query = builder.createQuery(Produto.class);
		Root<Produto> from = query.from(Produto.class);
		Predicate predicate = builder.and();

		if (ean != "") {
			predicate = builder.and(predicate, builder.equal(from.get("ean"), ean));
		}

		if (descricao != "") {
			predicate = builder.and(predicate,
					builder.like(builder.lower(from.get("descricao")), "%" + descricao.toString().toLowerCase() + "%"));
		}

		TypedQuery<Produto> typedQuery = em
				.createQuery(query.select(from).where(predicate).orderBy(builder.asc(from.get("codigo"))));
		return typedQuery.getResultList();
	}

	public void gravar(Produto produto) {
		try {
			em.getTransaction().begin();
			em.merge(produto);
			em.getTransaction().commit();
		} catch (Exception e) {
			PdvUtils.mensagem(FacesMessage.SEVERITY_ERROR, "Erro", "Erro ao gravar produto");
		}

	}

	public void ajusteEstoque(ProdutoAjusteEstoque produtoAjuste) {
		try {
			em.getTransaction().begin();
			em.persist(produtoAjuste);
			Produto produto = produtoAjuste.getProduto();
			produto.setEstoque(ERPUtils.calculaAjusteEstoque(produtoAjuste));
			em.merge(produto);
			em.getTransaction().commit();
		} catch (Exception e) {
			PdvUtils.mensagem(FacesMessage.SEVERITY_ERROR, "Erro ao ajustar estoque",
					"O ajuste de estoque para o material " + produtoAjuste.getProduto().getDescricao()
							+ " não pode ser efetivado.");
		}
	}

	public List<ProdutoAjusteEstoque> buscaAjustes(RelatorioProdutoEstoqueDTO relatorioProdutoEstoqueDTO) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<ProdutoAjusteEstoque> query = builder.createQuery(ProdutoAjusteEstoque.class);
		Root<ProdutoAjusteEstoque> from = query.from(ProdutoAjusteEstoque.class);
		Predicate predicate = builder.and();

		Join<ProdutoAjusteEstoque, Produto> joinProduto = from.join("produto");
		Join<ProdutoAjusteEstoque, TipoAjuste> joinTipoAjuste = from.join("tipoAjuste");

		predicate = builder.between(from.get("data"),
				DataUtils.criaDataInicial(relatorioProdutoEstoqueDTO.getDataInicio()),
				DataUtils.criaDataFinal(relatorioProdutoEstoqueDTO.getDataFinal()));

		if (relatorioProdutoEstoqueDTO.getProduto() != null) {
			predicate = builder.and(predicate,
					builder.equal(joinProduto.get("codigo"), relatorioProdutoEstoqueDTO.getProduto().getCodigo()));
		}

		if (relatorioProdutoEstoqueDTO.getTipoAjuste() != null) {
			predicate = builder.and(predicate, builder.equal(joinTipoAjuste.get("codigo"),
					relatorioProdutoEstoqueDTO.getTipoAjuste().getCodigo()));
		}

		TypedQuery<ProdutoAjusteEstoque> typedQuery = em
				.createQuery(query.select(from).where(predicate).orderBy(builder.asc(from.get("codigo"))));
		return typedQuery.getResultList();
	}
}