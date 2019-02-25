package br.com.htisoftware.pdv.util;

import java.util.Calendar;
import java.util.TimeZone;

public class DataUtils {

	public static Calendar criaDataInicial(Calendar data) {
		Calendar dataInicial = Calendar.getInstance();
		dataInicial.set(Calendar.HOUR, 00);
		dataInicial.set(Calendar.MINUTE, 00);
		dataInicial.set(Calendar.SECOND, 00);
		dataInicial.set(Calendar.DAY_OF_MONTH, data.get(Calendar.DAY_OF_MONTH));
		dataInicial.set(Calendar.MONTH, data.get(Calendar.MONTH));
		dataInicial.set(Calendar.YEAR, data.get(Calendar.YEAR));
		dataInicial.setTimeZone(TimeZone.getTimeZone("America/Sao_Paulo"));
		return dataInicial;
	}

	public static Calendar criaDataFinal(Calendar data) {
		Calendar dataFinal = Calendar.getInstance();
		dataFinal.set(Calendar.HOUR, 23);
		dataFinal.set(Calendar.MINUTE, 59);
		dataFinal.set(Calendar.SECOND, 59);
		dataFinal.set(Calendar.DAY_OF_MONTH, data.get(Calendar.DAY_OF_MONTH));
		dataFinal.set(Calendar.MONTH, data.get(Calendar.MONTH));
		dataFinal.set(Calendar.YEAR, data.get(Calendar.YEAR));
		dataFinal.setTimeZone(TimeZone.getTimeZone("America/Sao_Paulo"));
		return dataFinal;
	}
}
