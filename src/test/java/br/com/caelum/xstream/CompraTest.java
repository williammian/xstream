package br.com.caelum.xstream;

import com.thoughtworks.xstream.XStream;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
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
    public void deveSerializarLivroEMusica() {
        String xmlEsperado = "<compra>\n" +
                "  <id>15</id>\n" +
                "  <produtos class=\"linked-list\">\n" +
                "    <livro codigo=\"1589\">\n" +
                "      <nome>O Pássaro Raro</nome>\n" +
                "      <preco>100.0</preco>\n" +
                "      <descrição>dez histórias sobre a existência</descrição>\n" +
                "    </livro>\n" +
                "    <musica codigo=\"1590\">\n" +
                "      <nome>Meu Passeio</nome>\n" +
                "      <preco>100.0</preco>\n" +
                "      <descrição>música livre</descrição>\n" +
                "    </musica>\n" +
                "  </produtos>\n" +
                "</compra>";

        Compra compra = compraComLivroEMusica();

        XStream xstream = xstreamParaCompraEProduto();

        String xmlGerado = xstream.toXML(compra);
        assertEquals(xmlEsperado, xmlGerado);
    }

    @Test
    public void deveSerializarColecoesImplicitas() {
        String xmlEsperado = "<compra>\n" +
                "  <id>15</id>\n" +
                "  <produto codigo=\"1587\">\n" +
                "    <nome>geladeira</nome>\n" +
                "    <preco>1000.0</preco>\n" +
                "    <descrição>geladeira duas portas</descrição>\n" +
                "  </produto>\n" +
                "  <produto codigo=\"1588\">\n" +
                "    <nome>ferro de passar</nome>\n" +
                "    <preco>100.0</preco>\n" +
                "    <descrição>ferro com vaporizador</descrição>\n" +
                "  </produto>\n" +
                "</compra>";

        Compra compra = compraComGeladeiraEFerro();

        XStream xstream = xstreamParaCompraEProduto();
        xstream.addImplicitCollection(Compra.class, "produtos");

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

    @Test
    public void deveSerializarDuasGeladeirasIguais() {
        String xmlEsperado = "<compra>\n" +
                "  <id>15</id>\n" +
                "  <produtos>\n" +
                "    <produto codigo=\"1587\">\n" +
                "      <nome>geladeira</nome>\n" +
                "      <preco>1000.0</preco>\n" +
                "      <descrição>geladeira duas portas</descrição>\n" +
                "    </produto>\n" +
                "    <produto codigo=\"1587\">\n" +
                "      <nome>geladeira</nome>\n" +
                "      <preco>1000.0</preco>\n" +
                "      <descrição>geladeira duas portas</descrição>\n" +
                "    </produto>\n" +
                "  </produtos>\n" +
                "</compra>";

        Compra compra = compraDuasGeladeirasIguais();
        XStream xstream = xstreamParaCompraEProduto();
        xstream.setMode(XStream.NO_REFERENCES);
        String xmlGerado = xstream.toXML(compra);
        assertEquals(xmlEsperado, xmlGerado);

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

    private Compra compraDuasGeladeirasIguais() {
        Produto geladeira = geladeira();

        List<Produto> produtos = new ArrayList<>();
        produtos.add(geladeira);
        produtos.add(geladeira);
        return new Compra(15, produtos);
    }

    private Compra compraComLivroEMusica() {
        Produto livro = new Livro("O Pássaro Raro", 100.0, "dez histórias sobre a existência", 1589);
        Produto musica = new Musica("Meu Passeio", 100.0, "música livre", 1590);

        List<Produto> produtos = new LinkedList<>();
        produtos.add(livro);
        produtos.add(musica);
        return new Compra(15, produtos);
    }

    private XStream xstreamParaCompraEProduto() {
        XStream xstream = new XStream();
        xstream.alias("compra", Compra.class);
        xstream.alias("produto", Produto.class);
        xstream.alias("livro", Livro.class);
        xstream.alias("musica", Musica.class);
        xstream.aliasField("descrição", Produto.class, "descricao");
        xstream.useAttributeFor(Produto.class, "codigo");
        return xstream;
    }

}
