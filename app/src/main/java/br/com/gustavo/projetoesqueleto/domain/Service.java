package br.com.gustavo.projetoesqueleto.domain;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.List;

import br.com.gustavo.projetoesqueleto.utils.HttpHelper;

/**
 * Created by joao_ on 21/08/2016.
 */
public class Service {

    public static String URL = "http://tradesniffer-env.sa-east-1.elasticbeanstalk.com/rest/";

    public List<Empresas> getEmpresas(Pais pais) throws IOException {
        HttpHelper httpHelper = new HttpHelper();
        httpHelper.setContentType("application/json");

        String json = httpHelper.doGet(URL + "empresas/getEmpresasByPais/" + pais.getId());

        Gson gson = new Gson();
        List<Empresas> list =  gson.fromJson(json, new TypeToken<List<Empresas>>() {
        }.getType());
        return list;
    }

    public static List<Produto> getProdutos() throws IOException {
        HttpHelper httpHelper = new HttpHelper();
        httpHelper.setContentType("application/json");

        String json = httpHelper.doGet(URL + "produto/getProdutos");

        Gson gson = new Gson();
        List<Produto> list =  gson.fromJson(json, new TypeToken<List<Empresas>>() {
        }.getType());

        return list;
    }

}
