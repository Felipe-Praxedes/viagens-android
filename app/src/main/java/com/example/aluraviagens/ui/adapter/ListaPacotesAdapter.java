package com.example.aluraviagens.ui.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aluraviagens.R;
import com.example.aluraviagens.ui.util.MoedaUtil;
import com.example.aluraviagens.ui.model.Pacote;

import java.util.List;

public class ListaPacotesAdapter extends BaseAdapter {

    private final List<Pacote> pacotes;
    private final Context context;

    public ListaPacotesAdapter(List<Pacote> pacotes, Context context) {
        this.pacotes = pacotes;
        this.context = context;
    }

    @Override
    public int getCount() {
        return pacotes.size();
    }

    @Override
    public Pacote getItem(int posicao) {
        return pacotes.get(posicao);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int posicao, View view, ViewGroup parent) {
        View viewCriada = LayoutInflater.from(context)
                .inflate(R.layout.item_pacote, parent, false);

        Pacote pacote = pacotes.get(posicao);

        mostraLocal(viewCriada, pacote);
        mostraImagem(viewCriada, pacote);
        mostraDias(viewCriada, pacote);
        MostraPreco(viewCriada, pacote);

        return viewCriada;
    }

    private void MostraPreco(View viewCriada, Pacote pacote) {
        TextView preco = viewCriada.findViewById(R.id.item_pacote_preco);
        String moedaBrasileira = MoedaUtil.formataParaBrasileiro(pacote.getPreco());
        preco.setText(moedaBrasileira);
    }

    private void mostraDias(View viewCriada, Pacote pacote) {
        TextView dias = viewCriada.findViewById(R.id.item_pacote_dias);
        int quantidadeDias = pacote.getDias();

        if (quantidadeDias > 1) {
            dias.setText(pacote.getDias() + " dias");
        } else {
            dias.setText(pacote.getDias() + " dia");
        }
    }

    private void mostraImagem(View viewCriada, Pacote pacote) {
        ImageView imagem = viewCriada.findViewById(R.id.resumo_pacote_imagem);
        Drawable drawableImagemPacote = getDrawable(pacote);
        imagem.setImageDrawable(drawableImagemPacote);
    }

    private Drawable getDrawable(Pacote pacote) {
        Resources resources = context.getResources();
        int idDoDrawable = resources.getIdentifier(pacote.getImagem(), "drawable", context.getPackageName());
        Drawable drawableImagemPacote = resources.getDrawable(idDoDrawable);
        return drawableImagemPacote;
    }

    private void mostraLocal(View viewCriada, Pacote pacote) {
        TextView local = viewCriada.findViewById(R.id.item_pacote_local);
        local.setText(pacote.getLocal());
    }
}
