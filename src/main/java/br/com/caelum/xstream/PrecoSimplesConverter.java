package br.com.caelum.xstream;

import com.thoughtworks.xstream.converters.ConversionException;
import com.thoughtworks.xstream.converters.SingleValueConverter;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class PrecoSimplesConverter implements SingleValueConverter {

    @Override
    public boolean canConvert(Class type) {
        return type.isAssignableFrom(Double.class);
    }

    @Override
    public Object fromString(String valor) {
        NumberFormat formatter = getFormatador();
        try {
            return formatter.parse(valor);
        } catch (ParseException e) {
            throw new ConversionException("Não consegui converter " + valor, e);
        }
    }

    @Override
    public String toString(Object valor) {
        return getFormatador().format(valor);
    }

    private NumberFormat getFormatador() {
        Locale brasil = new Locale("pt", "br");
        return NumberFormat.getCurrencyInstance(brasil);
    }

}
