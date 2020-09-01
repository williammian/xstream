package br.com.caelum.xstream;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.TreeMarshaller;
import org.junit.Test;

public class CategoriaTest {

    @Test(expected = TreeMarshaller.CircularReferenceException.class)
    public void deveSerializarUmCiclo() {
        Categoria esporte = new Categoria(null, "esporte");
        Categoria futebol = new Categoria(esporte, "futebol");
        Categoria geral = new Categoria(futebol, "geral");
        esporte.setPai(geral);

        XStream xStream = new XStream();
        xStream.setMode(XStream.NO_REFERENCES);
        xStream.alias("categoria", Categoria.class);
        xStream.toXML(esporte);
    }

}
