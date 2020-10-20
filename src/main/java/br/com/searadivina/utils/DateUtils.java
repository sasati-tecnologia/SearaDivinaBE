package br.com.searadivina.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

	public static String formatDate(String dateStr) throws ParseException {
		DateFormat formatUS = new SimpleDateFormat("yyyy-mm-dd");
		Date date = formatUS.parse(dateStr);
		DateFormat formatBR = new SimpleDateFormat("dd/mm/yyyy");
		String dateFormated = formatBR.format(date);
		return dateFormated;
	}

}
