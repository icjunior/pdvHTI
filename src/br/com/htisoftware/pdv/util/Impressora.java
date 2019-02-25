package br.com.htisoftware.pdv.util;

import br.com.daruma.jna.DUAL;
import br.com.daruma.jna.SAT;

public class Impressora {

	public static int imprimirCupomSAT(String texto) {
		try {
			return SAT.tCFeEnviar_SAT(texto);
		} catch (Exception e) {
			return -50;
		}
	}

	public static int imprimirCupomPreVenda(String cupomPreVenda) {
		try {
			return DUAL.iImprimirTexto(cupomPreVenda, 0);
		} catch (Exception e) {
			return -50;
		}
	}
}
