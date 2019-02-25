package br.com.htisoftware.pdv.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.htisoftware.pdv.enums.TipoOperacao;
import br.com.htisoftware.pdv.modelo.Cupom;
import br.com.htisoftware.pdv.modelo.Usuario;
import br.com.htisoftware.pdv.service.CupomService;
import br.com.htisoftware.pdv.service.UsuarioService;

@Named
@ViewScoped
public class RelatorioCupomBean implements Serializable {

	private static final long serialVersionUID = 4600443631442280723L;
	private int operacao;
	private Calendar dataInicial;
	private Calendar dataFinal;
	private int pdv;
	private Usuario operador;
	private List<Usuario> operadores;
	@Inject
	UsuarioService usuarioService;
	@Inject
	CupomService cupomService;
	List<Cupom> cupons;

	@PostConstruct
	private void init() {
		operadores = usuarioService.findAll();
	}

	public void buscar() {
		cupons = cupomService.buscaCupons(operacao, dataInicial, dataFinal, pdv, operador);
	}

	public int getOperacao() {
		return operacao;
	}

	public void setOperacao(int operacao) {
		this.operacao = operacao;
	}

	public Calendar getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(Calendar dataInicial) {
		this.dataInicial = dataInicial;
	}

	public Calendar getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Calendar dataFinal) {
		this.dataFinal = dataFinal;
	}

	public int getPdv() {
		return pdv;
	}

	public void setPdv(int pdv) {
		this.pdv = pdv;
	}

	public Usuario getOperador() {
		return operador;
	}

	public void setOperador(Usuario operador) {
		this.operador = operador;
	}

	public List<Usuario> getOperadores() {
		return operadores;
	}

	public void setOperadores(List<Usuario> operadores) {
		this.operadores = operadores;
	}

	public List<Cupom> getCupons() {
		return cupons;
	}

	public void setCupons(List<Cupom> cupons) {
		this.cupons = cupons;
	}

	public BigDecimal getValorTotal() {
		if (cupons == null) {
			return new BigDecimal(0.00);
		}
		return cupons.stream().map(cupom -> cupom.getValor()).reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	public BigDecimal getValorTotalSAT() {
		if (cupons == null) {
			return new BigDecimal(0.00);
		}
		return cupons.stream()
				.filter(cupom -> !cupom.isPreVenda() && cupom.getOperacao().equals(TipoOperacao.DESCRICAO.VENDA))
				.map(cupom -> cupom.getValor()).reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	public BigDecimal getValorTotalPreVenda() {
		if (cupons == null) {
			return new BigDecimal(0.00);
		}
		return cupons.stream().filter(cupom -> cupom.isPreVenda()).map(cupom -> cupom.getValor())
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	}
}
