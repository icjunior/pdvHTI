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

@Entity
public class Cupom implements Serializable {

	private static final long serialVersionUID = -8529079883502280332L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cupom_sequence")
	@SequenceGenerator(name = "cupom_sequence", sequenceName = "cupom_sequence")
	private int codigo;
	@ManyToOne
	private Comanda comanda;
	@ManyToOne
	private Usuario usuario;
	private Calendar data;
	private BigDecimal valor;
	@ManyToOne
	private Cliente cliente;
	private boolean cupomCancelado;
	@OneToMany(mappedBy = "cupom")
	private List<CupomItem> cupomItens;
	@OneToMany(mappedBy = "cupom")
	private List<CupomPagamento> cupomPagamentos;
	@ManyToOne
	private Loja loja;
	private int terminal;
	private BigDecimal troco = new BigDecimal(0.00);
	private String cpfCnpj;
	private String operacao;
	private String xml;
	private String chaveAcesso;
	private boolean preVenda;
	private String observacao;

	public Cupom() {
	}

	public Cupom(Calendar data, BigDecimal valor, Usuario usuario, boolean cancelado, int terminal, BigDecimal troco,
			String cpfCnpj, String operacao, Loja loja, boolean preVenda, String observacao) {
		this.data = data;
		this.valor = valor;
		this.usuario = usuario;
		this.cupomCancelado = cancelado;
		this.terminal = terminal;
		this.troco = troco;
		this.cpfCnpj = cpfCnpj;
		this.operacao = operacao;
		this.loja = loja;
		this.preVenda = preVenda;
		this.observacao = observacao;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public Comanda getComanda() {
		return comanda;
	}

	public void setComanda(Comanda comanda) {
		this.comanda = comanda;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data) {
		this.data = data;
	}

	public boolean isCupomCancelado() {
		return cupomCancelado;
	}

	public void setCupomCancelado(boolean cupomCancelado) {
		this.cupomCancelado = cupomCancelado;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<CupomItem> getCupomItens() {
		return cupomItens;
	}

	public void setCupomItens(List<CupomItem> cupomItens) {
		this.cupomItens = cupomItens;
	}

	public List<CupomPagamento> getCupomPagamentos() {
		return cupomPagamentos;
	}

	public void setCupomPagamentos(List<CupomPagamento> cupomPagamentos) {
		this.cupomPagamentos = cupomPagamentos;
	}

	public Loja getLoja() {
		return loja;
	}

	public void setLoja(Loja loja) {
		this.loja = loja;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public int getTerminal() {
		return terminal;
	}

	public void setTerminal(int terminal) {
		this.terminal = terminal;
	}

	public BigDecimal getTroco() {
		return troco;
	}

	public void setTroco(BigDecimal troco) {
		this.troco = troco;
	}

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public String getOperacao() {
		return operacao;
	}

	public void setOperacao(String operacao) {
		this.operacao = operacao;
	}

	public String getXml() {
		return xml;
	}

	public void setXml(String xml) {
		this.xml = xml;
	}

	public String getChaveAcesso() {
		return chaveAcesso;
	}

	public void setChaveAcesso(String chaveAcesso) {
		this.chaveAcesso = chaveAcesso;
	}

	public boolean isPreVenda() {
		return preVenda;
	}

	public void setPreVenda(boolean preVenda) {
		this.preVenda = preVenda;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
}
