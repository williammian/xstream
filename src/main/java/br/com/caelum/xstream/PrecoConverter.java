package br.com.caelum.xstream;

import com.thoughtworks.xstream.converters.ConversionException;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class PrecoConverter implements Converter {

    @Override
    public boolean canConvert(Class type) {
        return type.isAssignableFrom(Double.class);
    }

    @Override
    public void marshal(Object object, HierarchicalStreamWriter writer, MarshallingContext context) {
        Double valor = (Double) object;
        NumberFormat formatter = getFormatador();
        String valorEmString = formatter.format(valor);

        writer.setValue(valorEmString);
    }

    @Override
    public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
        String value = reader.getValue();
        try {
            return getFormatador().parse(value);
        } catch (ParseException e) {
            throw new ConversionException("Não consegui converter " + value, e);
        }
    }

    private NumberFormat getFormatador() {
        Locale brasil = new Locale("pt", "br");
        return NumberFormat.getCurrencyInstance(brasil);
    }

}
