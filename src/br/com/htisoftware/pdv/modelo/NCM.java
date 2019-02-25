package br.com.htisoftware.pdv.modelo;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class NCM implements Serializable {

	private static final long serialVersionUID = -7116051258691719187L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ncm_sequence")
	@SequenceGenerator(name = "ncm_sequence", sequenceName = "ncm_sequence")
	private int codigo;
	private String ncm;
	private String categoria;
	private String descricao;
	private String ipi;
	@Temporal(TemporalType.DATE)
	private Calendar dataInicio;
	@Temporal(TemporalType.DATE)
	private Calendar dataFim;
	private String uTrib;
	private String descricaoUtrib;
	private Calendar gTinProducao;
	private Calendar gTinHomologacao;
	private String observacao;

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getIpi() {
		return ipi;
	}

	public void setIpi(String ipi) {
		this.ipi = ipi;
	}

	public String getuTrib() {
		return uTrib;
	}

	public void setuTrib(String uTrib) {
		this.uTrib = uTrib;
	}

	public String getDescricaoUtrib() {
		return descricaoUtrib;
	}

	public void setDescricaoUtrib(String descricaoUtrib) {
		this.descricaoUtrib = descricaoUtrib;
	}

	public Calendar getgTinProducao() {
		return gTinProducao;
	}

	public void setgTinProducao(Calendar gTinProducao) {
		this.gTinProducao = gTinProducao;
	}

	public Calendar getgTinHomologacao() {
		return gTinHomologacao;
	}

	public void setgTinHomologacao(Calendar gTinHomologacao) {
		this.gTinHomologacao = gTinHomologacao;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Calendar getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Calendar dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Calendar getDataFim() {
		return dataFim;
	}

	public void setDataFim(Calendar dataFim) {
		this.dataFim = dataFim;
	}

	public String getNcm() {
		return ncm;
	}

	public void setNcm(String ncm) {
		this.ncm = ncm;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((categoria == null) ? 0 : categoria.hashCode());
		result = prime * result + codigo;
		result = prime * result + ((dataFim == null) ? 0 : dataFim.hashCode());
		result = prime * result + ((dataInicio == null) ? 0 : dataInicio.hashCode());
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((descricaoUtrib == null) ? 0 : descricaoUtrib.hashCode());
		result = prime * result + ((gTinHomologacao == null) ? 0 : gTinHomologacao.hashCode());
		result = prime * result + ((gTinProducao == null) ? 0 : gTinProducao.hashCode());
		result = prime * result + ((ipi == null) ? 0 : ipi.hashCode());
		result = prime * result + ((ncm == null) ? 0 : ncm.hashCode());
		result = prime * result + ((observacao == null) ? 0 : observacao.hashCode());
		result = prime * result + ((uTrib == null) ? 0 : uTrib.hashCode());
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
		NCM other = (NCM) obj;
		if (categoria == null) {
			if (other.categoria != null)
				return false;
		} else if (!categoria.equals(other.categoria))
			return false;
		if (codigo != other.codigo)
			return false;
		if (dataFim == null) {
			if (other.dataFim != null)
				return false;
		} else if (!dataFim.equals(other.dataFim))
			return false;
		if (dataInicio == null) {
			if (other.dataInicio != null)
				return false;
		} else if (!dataInicio.equals(other.dataInicio))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (descricaoUtrib == null) {
			if (other.descricaoUtrib != null)
				return false;
		} else if (!descricaoUtrib.equals(other.descricaoUtrib))
			return false;
		if (gTinHomologacao == null) {
			if (other.gTinHomologacao != null)
				return false;
		} else if (!gTinHomologacao.equals(other.gTinHomologacao))
			return false;
		if (gTinProducao == null) {
			if (other.gTinProducao != null)
				return false;
		} else if (!gTinProducao.equals(other.gTinProducao))
			return false;
		if (ipi == null) {
			if (other.ipi != null)
				return false;
		} else if (!ipi.equals(other.ipi))
			return false;
		if (ncm == null) {
			if (other.ncm != null)
				return false;
		} else if (!ncm.equals(other.ncm))
			return false;
		if (observacao == null) {
			if (other.observacao != null)
				return false;
		} else if (!observacao.equals(other.observacao))
			return false;
		if (uTrib == null) {
			if (other.uTrib != null)
				return false;
		} else if (!uTrib.equals(other.uTrib))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return ncm;
	}
}
