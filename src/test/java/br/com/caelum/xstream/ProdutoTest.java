package br.com.caelum.xstream;

import com.thoughtworks.xstream.XStream;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ProdutoTest {

    @Test
    public void deveGerarXmlComNomePrecoDescricaoCodigoAdequados() {
        String xmlEsperado = "<produto codigo=\"1587\">\n" +
                "  <nome>Geladeira</nome>\n" +
                "  <preco>1000.0</preco>\n" +
                "  <descrição>Geladeira Duas Portas</descrição>\n" +
                "</produto>";

        Produto geladeira = new Produto("Geladeira", 1000.0, "Geladeira Duas Portas", 1587);

        XStream xstream = new XStream();
        xstream.alias("produto", Produto.class);
        xstream.aliasField("descrição", Produto.class, "descricao");
        xstream.useAttributeFor(Produto.class, "codigo");
        String xmlGerado = xstream.toXML(geladeira);

        assertEquals(xmlEsperado, xmlGerado);
    }

}
