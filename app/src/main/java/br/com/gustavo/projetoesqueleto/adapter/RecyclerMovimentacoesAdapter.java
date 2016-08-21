package br.com.gustavo.projetoesqueleto.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import br.com.gustavo.projetoesqueleto.R;
import br.com.gustavo.projetoesqueleto.domain.BalancaComercial;
import br.com.gustavo.projetoesqueleto.utils.MoedaUtils;

/**
 * Created by joao_ on 21/08/2016.
 */
public class RecyclerMovimentacoesAdapter extends RecyclerView.Adapter<RecyclerMovimentacoesAdapter.MovimentacoesViewHolder> {

    private final LayoutInflater inflater;
    private Context context;
    private List<BalancaComercial> list;
    private MovimentacoesCallback movimentacoesCallback;

    public interface MovimentacoesCallback {
        void onClickItem(BalancaComercial balancaComercial);
    }

    public RecyclerMovimentacoesAdapter(Context context, List<BalancaComercial> list , MovimentacoesCallback callback) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.list = list;
        this.movimentacoesCallback = callback;
    }

    @Override
    public MovimentacoesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.adapter_movimentacoes, parent, false);
        MovimentacoesViewHolder holder = new MovimentacoesViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MovimentacoesViewHolder holder, int position) {
        BalancaComercial balancaComercial = list.get(position);
        holder.tTexto.setText(balancaComercial.getPais().getNome());
        holder.tPreco.setText(MoedaUtils.format(balancaComercial.getValorTotal()));
        holder.lItem.setOnClickListener(onClickItem(balancaComercial));
    }

    private View.OnClickListener onClickItem(final BalancaComercial balancaComercial) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (movimentacoesCallback != null) {
                    movimentacoesCallback.onClickItem(balancaComercial);
                }
            }
        };
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MovimentacoesViewHolder extends RecyclerView.ViewHolder {

        TextView tTexto;
        TextView tPreco;
        LinearLayout lItem;

        public MovimentacoesViewHolder(View itemView) {
            super(itemView);

            tTexto = (TextView) itemView.findViewById(R.id.tTexto);
            tPreco = (TextView) itemView.findViewById(R.id.tPreco);
            lItem = (LinearLayout) itemView.findViewById(R.id.lItem);

        }
    }

}
