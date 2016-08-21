package br.com.gustavo.projetoesqueleto.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.gustavo.projetoesqueleto.R;
import br.com.gustavo.projetoesqueleto.domain.Produto;

/**
 * Created by Headtrap on 21-Aug-16.
 */
public class ProdutoAdapter  extends RecyclerView.Adapter<ProdutoAdapter.ProdutoViewHolder> {

    private Context context;
    private List<Produto> produtos = new ArrayList<>();
    private ProdutoCallback callback;

    public ProdutoAdapter (Context context, List<Produto> produtos, ProdutoCallback callback) {
        this.context = context;
        this.produtos = produtos;
        this.callback = callback;
    }

    @Override
    public ProdutoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_produto, parent, false);
        ProdutoViewHolder holder = new ProdutoViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ProdutoViewHolder holder, int position) {
        Produto produto = produtos.get(position);

        holder.tvDesc.setText(String.valueOf(produto.getDescricao()));
        holder.tvNcm.setText(String.valueOf(produto.getNcm()));
        holder.mLinear.setOnClickListener(onClickItem(produto));


    }

    @Override
    public int getItemCount() {
        return produtos.size();
    }

    public static class ProdutoViewHolder extends RecyclerView.ViewHolder {
        private TextView tvDesc;
        private LinearLayout mLinear;
        private TextView tvNcm;



        public ProdutoViewHolder(View itemView) {
            super(itemView);
            tvDesc = (TextView) itemView.findViewById(R.id.tvNomeDestino);
            mLinear = (LinearLayout) itemView.findViewById(R.id.lDestino);
            tvNcm = (TextView) itemView.findViewById(R.id.tvCategoriaDestino);

        }
    }

    private View.OnClickListener onClickItem(final Produto produto) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.onClickItem(produto);
            }
        };
    }

    public interface ProdutoCallback {
        void onClickItem(Produto produto);
    }

}