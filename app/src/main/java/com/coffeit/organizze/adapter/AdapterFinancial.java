package com.coffeit.organizze.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.coffeit.organizze.R;
import com.coffeit.organizze.model.Financial;

import java.util.List;

public class AdapterFinancial extends RecyclerView.Adapter<AdapterFinancial.MyViewHolder> {

    List<Financial> movimentacoes;
    Context context;

    public AdapterFinancial(List<Financial> movimentacoes, Context context) {
        this.movimentacoes = movimentacoes;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_financial, parent, false);
        return new MyViewHolder(itemLista);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Financial movimentacao = movimentacoes.get(position);

        holder.titulo.setText(movimentacao.getDesc());
        holder.valor.setText(String.valueOf(movimentacao.getValue()));
        holder.categoria.setText(movimentacao.getCategory());

        if (movimentacao.getType() == "d" || movimentacao.getType().equals("d")) {
            holder.valor.setTextColor(context.getResources().getColor(R.color.colorAccent));
            holder.valor.setText("-" + movimentacao.getValue());
        }
    }


    @Override
    public int getItemCount() {
        return movimentacoes.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView titulo, valor, categoria;

        public MyViewHolder(View itemView) {
            super(itemView);

            titulo = itemView.findViewById(R.id.textAdapterTitulo);
            valor = itemView.findViewById(R.id.textAdapterValor);
            categoria = itemView.findViewById(R.id.textAdapterCategoria);
        }

    }

}
