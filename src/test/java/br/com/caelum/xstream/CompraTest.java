package br.com.caelum.xstream;

import com.thoughtworks.xstream.XStream;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CompraTest {

    @Test
    public void deveGerarCadaUmDosProdutosDeUmaCompra() {
        String xmlEsperado = "<compra>\n" +
                "  <id>15</id>\n" +
                "  <produtos>\n" +
                "    <produto codigo=\"1587\">\n" +
                "      <nome>geladeira</nome>\n" +
                "      <preco>1000.0</preco>\n" +
                "      <descrição>geladeira duas portas</descrição>\n" +
                "    </produto>\n" +
                "    <produto codigo=\"1588\">\n" +
                "      <nome>ferro de passar</nome>\n" +
                "      <preco>100.0</preco>\n" +
                "      <descrição>ferro com vaporizador</descrição>\n" +
                "    </produto>\n" +
                "  </produtos>\n" +
                "</compra>";


        Compra compra = compraComGeladeiraEFerro();

        XStream xstream = xstreamParaCompraEProduto();

        String xmlGerado = xstream.toXML(compra);
        assertEquals(xmlEsperado, xmlGerado);
    }

    @Test
    public void deveGerarUmaCompraComCadaUmDosProdutosDeUmXML() {
        String xmlDeOrigem = "<compra>\n" +
                "  <id>15</id>\n" +
                "  <produtos>\n" +
                "    <produto codigo=\"1587\">\n" +
                "      <nome>geladeira</nome>\n" +
                "      <preco>1000.0</preco>\n" +
                "      <descrição>geladeira duas portas</descrição>\n" +
                "    </produto>\n" +
                "    <produto codigo=\"1588\">\n" +
                "      <nome>ferro de passar</nome>\n" +
                "      <preco>100.0</preco>\n" +
                "      <descrição>ferro com vaporizador</descrição>\n" +
                "    </produto>\n" +
                "  </produtos>\n" +
                "</compra>";

        Compra compraEsperada = compraComGeladeiraEFerro();

        XStream xstream = xstreamParaCompraEProduto();

        Compra compraDesserializada = (Compra) xstream.fromXML(xmlDeOrigem);

        assertEquals(compraEsperada, compraDesserializada);
    }

    private Produto geladeira() {
        return new Produto("geladeira", 1000.0, "geladeira duas portas", 1587);
    }

    private Produto ferro() {
        return new Produto("ferro de passar", 100.0, "ferro com vaporizador", 1588);
    }

    private Compra compraComGeladeiraEFerro() {
        Produto geladeira = geladeira();
        Produto ferro = ferro();

        List<Produto> produtos = new ArrayList<>();
        produtos.add(geladeira);
        produtos.add(ferro);
        return new Compra(15, produtos);
    }

    private XStream xstreamParaCompraEProduto() {
        XStream xstream = new XStream();
        xstream.alias("compra", Compra.class);
        xstream.alias("produto", Produto.class);
        xstream.aliasField("descrição", Produto.class, "descricao");
        xstream.useAttributeFor(Produto.class, "codigo");
        return xstream;
    }

}
