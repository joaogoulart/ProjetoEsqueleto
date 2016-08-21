package br.com.gustavo.projetoesqueleto.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;

import br.com.gustavo.projetoesqueleto.R;
import br.com.gustavo.projetoesqueleto.fragment.ListaProdutosFragment;

public class ListaProdutosActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_lista_produtos);

            setupToolbar();
            ActionBar actionbar = getSupportActionBar();
            if (actionbar != null) {
                actionbar.setTitle("Cadastro de cartão");
                actionbar.setDisplayHomeAsUpEnabled(true);
                actionbar.setHomeButtonEnabled(true);

                if (savedInstanceState == null) {
                    ListaProdutosFragment frag = new ListaProdutosFragment();
                    frag.setArguments(getIntent().getExtras());
                    replaceFragment(frag, R.id.container);
                }
            }
        }
}
