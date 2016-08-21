package br.com.gustavo.projetoesqueleto.fragment;


import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.com.gustavo.projetoesqueleto.activity.PaisesActivity;
import br.com.gustavo.projetoesqueleto.adapter.ProdutoAdapter;
import br.com.gustavo.projetoesqueleto.R;
import br.com.gustavo.projetoesqueleto.domain.Produto;
import br.com.gustavo.projetoesqueleto.domain.Service;


public class ListaProdutosFragment extends BaseFragment implements ProdutoAdapter.ProdutoCallback {


    private RecyclerView recyclerViewProdutos;
    private ProdutoAdapter adapter;
    private LinearLayoutManager mLayoutManager;
    private SwipeRefreshLayout swipeRefreshLayout;


    public ListaProdutosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lista_produtos, container, false);
        recyclerViewProdutos = (RecyclerView) view.findViewById(R.id.recyclerViewProdutos);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);
        mLayoutManager = new LinearLayoutManager(getContext());
        recyclerViewProdutos.setLayoutManager(mLayoutManager);
        recyclerViewProdutos.setItemAnimator(new DefaultItemAnimator());

        swipeRefreshLayout.setOnRefreshListener(onRefreshListener());
        swipeRefreshLayout.setColorSchemeResources(R.color.white);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        startTask("taskLoadProdutos", taskLoadProdutos(), R.id.progress);
    }

    private TaskListener taskLoadProdutos() {
        return new TaskListener<List<Produto>>() {
            @Override
            public List<Produto> execute() throws Exception {
                return Service.getProdutos();
            }


            @Override
            public void updateView(List<Produto> response) {
                adapter = new ProdutoAdapter(getContext(), response, ListaProdutosFragment.this);
                recyclerViewProdutos.setAdapter(adapter);

                if (swipeRefreshLayout != null) {
                    swipeRefreshLayout.setRefreshing(false);
                }
            }

            @Override
            public void onError(Exception exception) {

            }

            @Override
            public void onCancelled(String cod) {

            }
        };
    }

    private SwipeRefreshLayout.OnRefreshListener onRefreshListener() {
        return new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                startTask("taskLoadProdutos", taskLoadProdutos(), R.id.progress);
            }
        };
    }


    @Override
    public void onClickItem(Produto produto) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("produto", produto);
        show(getActivity(), PaisesActivity.class, bundle);
    }
}