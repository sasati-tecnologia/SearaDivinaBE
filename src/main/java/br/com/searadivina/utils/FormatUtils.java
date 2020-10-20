package br.com.searadivina.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class FormatUtils {

	public static Double formataDouble(double valor) {
		BigDecimal bd = new BigDecimal(valor).setScale(2, RoundingMode.HALF_EVEN);
		return bd.doubleValue();
	}

}
