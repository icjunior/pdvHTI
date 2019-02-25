package br.com.htisoftware.pdv.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.htisoftware.pdv.modelo.Departamento;
import br.com.htisoftware.pdv.service.DepartamentoService;
import br.com.htisoftware.pdv.util.PdvUtils;

@Named
@ViewScoped
public class DepartamentoBean implements Serializable {

	private static final long serialVersionUID = 3214676983812642209L;
	@Inject
	private Departamento departamento;
	@Inject
	DepartamentoService departamentoService;
	List<Departamento> departamentos;

	@PostConstruct
	private void init() {
		departamentos = departamentoService.findAll();
	}

	public void gravar() {
		departamentoService.gravar(departamento);

		PdvUtils.mensagem(FacesMessage.SEVERITY_INFO, "Inclusão de departamento", "Departamento criado com sucesso");
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public List<Departamento> getDepartamentos() {
		return departamentos;
	}

	public void setDepartamentos(List<Departamento> departamentos) {
		this.departamentos = departamentos;
	}
}
