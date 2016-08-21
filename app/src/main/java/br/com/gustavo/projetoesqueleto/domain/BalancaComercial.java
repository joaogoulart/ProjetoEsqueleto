package br.com.gustavo.projetoesqueleto.domain;

/**
 * Created by joao_ on 20/08/2016.
 */

public class BalancaComercial {

    public String tipo;

    public String PERIODO;

    public int coAno;

    public Pais pais;

    public Produto produto;

    public String fatAgreg;

    public String continente;

    public int valorTotal;
    //TIPO	PERIODO	CO_ANO	NO_PAIS	NO_PPE_PPI	NO_FAT_AGREG	CONTINENTE	VL_FOB

    private Long id;

    public Long getId() {
        return id;
    }

    public BalancaComercial() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getPERIODO() {
        return PERIODO;
    }

    public void setPERIODO(String PERIODO) {
        this.PERIODO = PERIODO;
    }

    public int getCoAno() {
        return coAno;
    }

    public void setCoAno(int coAno) {
        this.coAno = coAno;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais noPais) {
        this.pais = noPais;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto noPpePpi) {
        this.produto = noPpePpi;
    }

    public String getFatAgreg() {
        return fatAgreg;
    }

    public void setFatAgreg(String noFatAgreg) {
        this.fatAgreg = noFatAgreg;
    }

    public int getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(int vlFob) {
        this.valorTotal = vlFob;
    }

    public String getContinente() {
        return continente;
    }

    public void setContinente(String relatorio) {
        this.continente = relatorio;
    }
}
