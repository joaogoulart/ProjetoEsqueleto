package br.com.gustavo.projetoesqueleto.domain;

import java.io.Serializable;

/**
 * Created by joao_ on 20/08/2016.
 */
public class Pais implements Serializable {

    public String nome;

    public Pais(String nome) {
        this.nome = nome;
    }

    public Pais() {
    }



    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
