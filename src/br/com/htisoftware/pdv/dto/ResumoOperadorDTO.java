package br.com.htisoftware.pdv.dto;

import java.math.BigDecimal;
import java.util.List;

import br.com.htisoftware.pdv.enums.TipoOperacao;

public class ResumoOperadorDTO {

	private String operacao;
	private BigDecimal total;

	public ResumoOperadorDTO() {
	}

	public ResumoOperadorDTO(String operacao, BigDecimal total) {
		super();
		this.operacao = operacao;
		this.total = total;
	}

	public String getOperacao() {
		return operacao;
	}

	public void setOperacao(String operacao) {
		this.operacao = operacao;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public BigDecimal getTotalDinheiroNoPDV(List<ResumoOperadorDTO> resumo) {
		if (resumo == null) {
			return new BigDecimal("0.00");
		}
		BigDecimal entradas = new BigDecimal("0.00");
		BigDecimal saidas = new BigDecimal("0.00");

		entradas = resumo.stream().filter(p -> !p.getOperacao().equals(TipoOperacao.DESCRICAO.SANGRIA))
				.map(ResumoOperadorDTO::getTotal).reduce(BigDecimal::add).orElse(BigDecimal.ZERO);

		saidas = resumo.stream().filter(p -> p.getOperacao().equals(TipoOperacao.DESCRICAO.SANGRIA))
				.map(ResumoOperadorDTO::getTotal).reduce(BigDecimal::add).orElse(BigDecimal.ZERO);

		return entradas.subtract(saidas);
	}
}
