package br.com.caelum.xstream;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Compra {

    private int id;
    private List<Produto> produtos = new ArrayList<>();

    public Compra(int id, List<Produto> produtos) {
        this.id = id;
        this.produtos = produtos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Compra compra = (Compra) o;
        return id == compra.id &&
                Objects.equals(produtos, compra.produtos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, produtos);
    }
}
