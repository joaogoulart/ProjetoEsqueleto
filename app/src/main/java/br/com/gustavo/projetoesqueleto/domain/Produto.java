package br.com.gustavo.projetoesqueleto.domain;

/**
 * Created by joao_ on 20/08/2016.
 */
public class Produto {

    public String descricao;

    public Long ncm;

    public Long getNcm() {
        return ncm;
    }

    public Produto() {
    }

    public void setNcm(Long ncm) {
        this.ncm = ncm;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
