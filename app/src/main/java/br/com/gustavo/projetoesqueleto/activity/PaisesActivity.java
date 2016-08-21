package br.com.gustavo.projetoesqueleto.activity;

import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import java.io.IOException;

import br.com.gustavo.projetoesqueleto.R;
import br.com.gustavo.projetoesqueleto.adapter.RecyclerMovimentacoesAdapter;
import br.com.gustavo.projetoesqueleto.domain.BalancaComercial;
import br.com.gustavo.projetoesqueleto.domain.BalancaDTO;
import br.com.gustavo.projetoesqueleto.domain.Produto;
import br.com.gustavo.projetoesqueleto.domain.Service;

public class PaisesActivity extends BaseActivity implements RecyclerMovimentacoesAdapter.MovimentacoesCallback {

    private RecyclerView recyclerViewImport;
    private RecyclerView recyclerViewExport;

    private LinearLayout layoutRecycler;

    private Produto produto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paises);

        recyclerViewImport = (RecyclerView) findViewById(R.id.recyclerViewImport);
        recyclerViewImport.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        recyclerViewExport = (RecyclerView) findViewById(R.id.recyclerViewExport);
        recyclerViewExport.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        layoutRecycler = (LinearLayout) findViewById(R.id.layoutRecycler);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            produto = (Produto) extras.getSerializable("produto");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        startTask("taskGetPaises", taskGetPaises(), R.id.progress);
    }

    private TaskListener taskGetPaises() {
        return new TaskListener<BalancaDTO>() {
            @Override
            public BalancaDTO execute() throws Exception {
                BalancaDTO balancaDTO = Service.getPaises(produto);
                return balancaDTO;
            }

            @Override
            public void updateView(BalancaDTO response) throws IOException, Settings.SettingNotFoundException {
                RecyclerMovimentacoesAdapter recyclerMovimentacoesAdapterImpo = new RecyclerMovimentacoesAdapter(getApplicationContext(), response.listImportacao, PaisesActivity.this);
                recyclerViewImport.setAdapter(recyclerMovimentacoesAdapterImpo);
                RecyclerMovimentacoesAdapter recyclerMovimentacoesAdapterExpo = new RecyclerMovimentacoesAdapter(getApplicationContext(), response.listExportacao, PaisesActivity.this);
                recyclerViewExport.setAdapter(recyclerMovimentacoesAdapterExpo);

                layoutRecycler.setVisibility(View.VISIBLE);
            }

            @Override
            public void onError(Exception exception) {

            }

            @Override
            public void onCancelled(String cod) {

            }
        };
    }

    @Override
    public void onClickItem(BalancaComercial balancaComercial) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("pais", balancaComercial.getPais());
        show(PaisesActivity.this, MapsActivity.class, bundle);
    }
}
