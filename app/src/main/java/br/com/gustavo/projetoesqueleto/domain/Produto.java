package br.com.gustavo.projetoesqueleto.domain;

import java.io.Serializable;

/**
 * Created by joao_ on 20/08/2016.
 */
public class Produto implements Serializable {

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
