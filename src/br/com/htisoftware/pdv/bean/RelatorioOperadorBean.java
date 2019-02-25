package br.com.htisoftware.pdv.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.PrimeFaces;

import br.com.htisoftware.pdv.dto.ResumoOperadorDTO;
import br.com.htisoftware.pdv.service.UsuarioService;

@Named
@ViewScoped
public class RelatorioOperadorBean implements Serializable {

	private static final long serialVersionUID = -373468960858589231L;
	private int tipoRelatorio;
	private Calendar data;
	private int operador;
	@Inject
	UsuarioService usuarioService;
	private List<ResumoOperadorDTO> resumoOperador;
	private BigDecimal valorTotalOperador;

	public void pesquisar() {
		resumoOperador = usuarioService.relatorioPorOperador(data, operador);
		valorTotalOperador = usuarioService.valorTotalOperador(resumoOperador);
		PrimeFaces.current().executeScript("PF('operadorRelatorioDataTable').show()");
	}

	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data) {
		this.data = data;
	}

	public int getOperador() {
		return operador;
	}

	public void setOperador(int operador) {
		this.operador = operador;
	}

	public int getTipoRelatorio() {
		return tipoRelatorio;
	}

	public void setTipoRelatorio(int tipoRelatorio) {
		this.tipoRelatorio = tipoRelatorio;
	}

	public List<ResumoOperadorDTO> getResumoOperador() {
		return resumoOperador;
	}

	public void setResumoOperador(List<ResumoOperadorDTO> resumoOperador) {
		this.resumoOperador = resumoOperador;
	}

	public BigDecimal getValorTotalOperador() {
		return valorTotalOperador;
	}
}
