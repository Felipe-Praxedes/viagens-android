package com.example.aluraviagens.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aluraviagens.R;
import com.example.aluraviagens.ui.adapter.ListaPacotesAdapter;
import com.example.aluraviagens.ui.dao.PacoteDAO;
import com.example.aluraviagens.ui.model.Pacote;

import java.util.List;

public class ListaPacoteActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Pacotes";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_pacote);
        setTitle(TITULO_APPBAR);

        configuraLista();
    }

    private void configuraLista() {
        ListView listadePacotes = findViewById(R.id.lista_pacote_listview);
        final List<Pacote> pacotes = new PacoteDAO().lista();
        listadePacotes.setAdapter(new ListaPacotesAdapter(pacotes, this));
        listadePacotes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicao, long id) {
                Pacote pacoteClicado = pacotes.get(posicao);
                Intent intent = new Intent(ListaPacoteActivity.this, ResumoPacoteActivity.class);
                intent.putExtra("pacote", pacoteClicado);
                startActivity(intent);
            }
        });

    }
}