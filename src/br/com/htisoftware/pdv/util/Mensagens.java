package br.com.htisoftware.pdv.util;

import javax.faces.application.FacesMessage;

public class Mensagens {

	public static void mensagemSAT(int codigoRetorno) {
		switch (codigoRetorno) {
		case 1:
			break;
		case 2:
			PdvUtils.mensagem(FacesMessage.SEVERITY_INFO, "SAT Fiscal",
					"Comando de emissão CFe executado com sucesso, falha no envio do xml para backup");
			break;
		case 0:
			PdvUtils.mensagem(FacesMessage.SEVERITY_INFO, "SAT Fiscal",
					"Não foi possível comunicar com a impressora não fiscal");
			break;
		case -1:
			PdvUtils.mensagem(FacesMessage.SEVERITY_INFO, "SAT Fiscal", "Impressora Desligada");
			break;
		case -6:
			PdvUtils.mensagem(FacesMessage.SEVERITY_INFO, "SAT Fiscal", "TimeOut, erro de comunicação com o SAT");
			break;
		case -7:
			PdvUtils.mensagem(FacesMessage.SEVERITY_INFO, "SAT Fiscal", "Erro de comunicação com o SAT");
			break;
		case -27:
			PdvUtils.mensagem(FacesMessage.SEVERITY_INFO, "SAT Fiscal", "Erro genérico na impressora");
			break;
		case -50:
			PdvUtils.mensagem(FacesMessage.SEVERITY_INFO, "SAT Fiscal", "Impressora off-line");
			break;
		case -51:
			PdvUtils.mensagem(FacesMessage.SEVERITY_INFO, "SAT Fiscal", "Impressora sem papel");
			break;
		case -52:
			PdvUtils.mensagem(FacesMessage.SEVERITY_INFO, "SAT Fiscal", "Erro ao gravar/ler arquivo temporário");
			break;
		case -54:
			PdvUtils.mensagem(FacesMessage.SEVERITY_INFO, "SAT Fiscal", "Impressora com tampa aberta");
			break;
		case -99:
			PdvUtils.mensagem(FacesMessage.SEVERITY_INFO, "SAT Fiscal",
					"Parâmetro inválido ou ponteiro nulo de parâmetro");
			break;
		case -120:
			PdvUtils.mensagem(FacesMessage.SEVERITY_INFO, "SAT Fiscal", "TAG inválida");
			break;
		case -121:
			PdvUtils.mensagem(FacesMessage.SEVERITY_INFO, "SAT Fiscal", "Estrutura inválida");
			break;
		case -122:
			PdvUtils.mensagem(FacesMessage.SEVERITY_INFO, "SAT Fiscal", "TAG obrigatória não informada");
			break;
		case -123:
			PdvUtils.mensagem(FacesMessage.SEVERITY_INFO, "SAT Fiscal", "TAG obrigatória vazia");
			break;
		case -130:
			PdvUtils.mensagem(FacesMessage.SEVERITY_INFO, "SAT Fiscal", "CFe já aberto");
			break;
		case -136:
			PdvUtils.mensagem(FacesMessage.SEVERITY_INFO, "SAT Fiscal",
					"CFe em estado inválido para execução do método");
			break;
		case -140:
			PdvUtils.mensagem(FacesMessage.SEVERITY_INFO, "SAT Fiscal",
					"Biblioteca auxiliar dllsat.dll não foi encontrada/carregada");
			break;
		case -141:
			PdvUtils.mensagem(FacesMessage.SEVERITY_INFO, "SAT Fiscal",
					"Impressora inválida, o método só funciona com impressora Daruma DR700 ou superior, com SB versão 02.50.00 ou superior SAT/ MFE inválido, é necessário um equipamento da marca Daruma");
			break;
		case -142:
			PdvUtils.mensagem(FacesMessage.SEVERITY_INFO, "SAT Fiscal", "Resposta do SAT Incompleta");
			break;
		case 6001:
			PdvUtils.mensagem(FacesMessage.SEVERITY_INFO, "SAT Fiscal", "Código de ativação inválido");
			break;
		case 6002:
			PdvUtils.mensagem(FacesMessage.SEVERITY_INFO, "SAT Fiscal", "SAT ainda não ativado");
			break;
		case 6003:
			PdvUtils.mensagem(FacesMessage.SEVERITY_INFO, "SAT Fiscal", "SAT não vinculado ao AC");
			break;
		case 6004:
			PdvUtils.mensagem(FacesMessage.SEVERITY_INFO, "SAT Fiscal", "Vinculação do AC não confere");
			break;
		case 6005:
			PdvUtils.mensagem(FacesMessage.SEVERITY_INFO, "SAT Fiscal", "Tamanho do CFe superior a 1500KB");
			break;
		case 6006:
			PdvUtils.mensagem(FacesMessage.SEVERITY_INFO, "SAT Fiscal", "SAT bloqueado pelo contribuinte");
			break;
		case 6007:
			PdvUtils.mensagem(FacesMessage.SEVERITY_INFO, "SAT Fiscal", "SAT bloqueado pela SEFAZ");
			break;
		case 6008:
			PdvUtils.mensagem(FacesMessage.SEVERITY_INFO, "SAT Fiscal",
					"SAT bloqueado por falta de comunicaçãoSAT bloqueado por falta de comunicação");
			break;
		case 6009:
			PdvUtils.mensagem(FacesMessage.SEVERITY_INFO, "SAT Fiscal", "SAT bloqueado, código de ativação incorreto");
			break;
		case 6010:
			PdvUtils.mensagem(FacesMessage.SEVERITY_INFO, "SAT Fiscal", "Erro de validação do conteúdo");
			break;
		case 6098:
			PdvUtils.mensagem(FacesMessage.SEVERITY_INFO, "SAT Fiscal", "SAT em processamento. Tente novamente");
			break;
		case 6099:
			PdvUtils.mensagem(FacesMessage.SEVERITY_INFO, "SAT Fiscal", "Erro desconhecido na emissão");
			break;
		default:
			break;
		}
	}

	public static void mensagemTextoLivre(int codigoRetorno) {
		switch (codigoRetorno) {
		case 1:
			break;
		case 0:
			PdvUtils.mensagem(FacesMessage.SEVERITY_INFO, "Impressora não fiscal",
					"Erro de comunicação, não foi possível enviar o método");
			break;
		case -27:
			PdvUtils.mensagem(FacesMessage.SEVERITY_INFO, "Impressora não fiscal", "Erro Genérico");
			break;
		case -50:
			PdvUtils.mensagem(FacesMessage.SEVERITY_INFO, "Impressora não fiscal", "Impressora OFF-LINE");
			break;
		case -51:
			PdvUtils.mensagem(FacesMessage.SEVERITY_INFO, "Impressora não fiscal", "Impressora sem papel");
			break;
		case -52:
			PdvUtils.mensagem(FacesMessage.SEVERITY_INFO, "Impressora não fiscal", "Impressora inicializando");
			break;
		case -54:
			PdvUtils.mensagem(FacesMessage.SEVERITY_INFO, "Impressora não fiscal", "Tampa aberta");
			break;
		default:
			break;
		}
	}
}