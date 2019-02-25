package br.com.htisoftware.pdv.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "ean", name = "ean_uk"))
public class Produto implements Serializable {

	private static final long serialVersionUID = 3759242012655264880L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "produto_sequence")
	@SequenceGenerator(name = "produto_sequence", sequenceName = "produto_sequence")
	private int codigo;
	private String ean;
	private String descricao;
	@ManyToOne
	private Departamento departamento;
	@ManyToOne
	private Usuario usuario;
	private Calendar dataInclusao;
	private Calendar dataAlteracao;
	private BigDecimal valor;
	private boolean ativo;
	private BigDecimal estoque;
	@ManyToOne
	private NCM ncm;
	@ManyToOne
	private CEST cest;
	private boolean pis;
	@ManyToOne
	private CST cstPis;
	private boolean cofins;
	@ManyToOne
	private CST cstCofins;
	private boolean proibeVenda;
	private boolean proibeCompra;
	@ManyToOne
	private Aliquota aliquota;
	@OneToMany(mappedBy = "produto")
	private List<ProdutoAjusteEstoque> ajustes;

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getEan() {
		return ean;
	}

	public void setEan(String ean) {
		this.ean = ean;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Calendar getDataInclusao() {
		return dataInclusao;
	}

	public void setDataInclusao(Calendar dataInclusao) {
		this.dataInclusao = dataInclusao;
	}

	public Calendar getDataAlteracao() {
		return dataAlteracao;
	}

	public void setDataAlteracao(Calendar dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public BigDecimal getEstoque() {
		return estoque;
	}

	public void setEstoque(BigDecimal estoque) {
		this.estoque = estoque;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (ativo ? 1231 : 1237);
		result = prime * result + codigo;
		result = prime * result + ((dataAlteracao == null) ? 0 : dataAlteracao.hashCode());
		result = prime * result + ((dataInclusao == null) ? 0 : dataInclusao.hashCode());
		result = prime * result + ((departamento == null) ? 0 : departamento.hashCode());
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((ean == null) ? 0 : ean.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
		result = prime * result + ((valor == null) ? 0 : valor.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		if (ativo != other.ativo)
			return false;
		if (codigo != other.codigo)
			return false;
		if (dataAlteracao == null) {
			if (other.dataAlteracao != null)
				return false;
		} else if (!dataAlteracao.equals(other.dataAlteracao))
			return false;
		if (dataInclusao == null) {
			if (other.dataInclusao != null)
				return false;
		} else if (!dataInclusao.equals(other.dataInclusao))
			return false;
		if (departamento == null) {
			if (other.departamento != null)
				return false;
		} else if (!departamento.equals(other.departamento))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (ean == null) {
			if (other.ean != null)
				return false;
		} else if (!ean.equals(other.ean))
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		if (valor == null) {
			if (other.valor != null)
				return false;
		} else if (!valor.equals(other.valor))
			return false;
		return true;
	}

	public boolean isProibeVenda() {
		return proibeVenda;
	}

	public void setProibeVenda(boolean proibeVenda) {
		this.proibeVenda = proibeVenda;
	}

	public boolean isProibeCompra() {
		return proibeCompra;
	}

	public void setProibeCompra(boolean proibeCompra) {
		this.proibeCompra = proibeCompra;
	}

	public boolean isPis() {
		return pis;
	}

	public void setPis(boolean pis) {
		this.pis = pis;
	}

	public boolean isCofins() {
		return cofins;
	}

	public void setCofins(boolean cofins) {
		this.cofins = cofins;
	}

	public Aliquota getAliquota() {
		return aliquota;
	}

	public void setAliquota(Aliquota aliquota) {
		this.aliquota = aliquota;
	}

	public NCM getNcm() {
		return ncm;
	}

	public void setNcm(NCM ncm) {
		this.ncm = ncm;
	}

	public CEST getCest() {
		return cest;
	}

	public void setCest(CEST cest) {
		this.cest = cest;
	}

	public CST getCstPis() {
		return cstPis;
	}

	public void setCstPis(CST cstPis) {
		this.cstPis = cstPis;
	}

	public CST getCstCofins() {
		return cstCofins;
	}

	public void setCstCofins(CST cstCofins) {
		this.cstCofins = cstCofins;
	}

	public List<ProdutoAjusteEstoque> getAjustes() {
		return ajustes;
	}

	public void setAjustes(List<ProdutoAjusteEstoque> ajustes) {
		this.ajustes = ajustes;
	}
}