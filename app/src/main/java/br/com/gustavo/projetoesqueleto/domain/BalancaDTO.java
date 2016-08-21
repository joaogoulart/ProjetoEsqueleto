package br.com.gustavo.projetoesqueleto.domain;

import java.io.Serializable;
import java.util.List;

/**
 * Created by joao_ on 20/08/2016.
 */
public class BalancaDTO implements Serializable {

    public List<BalancaComercial> listImportacao;
    public List<BalancaComercial> listExportacao;

}
