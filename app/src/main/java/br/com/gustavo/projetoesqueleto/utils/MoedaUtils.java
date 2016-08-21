package br.com.gustavo.projetoesqueleto.utils;

import java.text.NumberFormat;

public class MoedaUtils {
    private static NumberFormat formatter = NumberFormat.getCurrencyInstance();

    public static String format(double value) {
        return formatter.format(value);
    }

    public static String format(String strValue) throws NumberFormatException {
        double value = Double.parseDouble(strValue);
        return format(value);
    }

}
