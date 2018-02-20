package br.ufrpe.beela.lugar.gui;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

import br.ufrpe.beela.gui.R;
import br.ufrpe.beela.lugar.dominio.Lugar;
import br.ufrpe.beela.lugar.negocio.AdapterCustomizado;
import br.ufrpe.beela.lugar.negocio.LugarService;

/**
 * Created by max on 06/01/18.
 */

public class LugarAct extends AppCompatActivity  {


    private ArrayList<Lugar> listLugar = new ArrayList<>();
    private ListView listViewLugares;
    private LugarService lugarService = new LugarService();
    Button maps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLugarRecuperado();
        setContentView(R.layout.activity_lugares);
        setListView();
    }

    public ArrayList<Lugar> getLugares() {
        return listLugar;
    }

    private void setListView() {
        listViewLugares = findViewById(R.id.listView2);
        AdapterCustomizado adapter = new AdapterCustomizado(getApplicationContext(), getLugares());
        listViewLugares.setAdapter(adapter);
        listViewLugares.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Lugar LugarSelecionado = (Lugar) parent.getAdapter().getItem(position);
                irTelaDescricaoLugar(LugarSelecionado);


            }
        });

    }
    public void irTelaDescricaoLugar(Lugar lugarzinho) {
        Intent intent = new Intent(LugarAct.this,LugarDetalhesAct.class);
        intent.putExtra(getString(R.string.lugar), (Serializable) lugarzinho);
        startActivity(intent);
    }

    public void getLugarRecuperado(){
        ArrayList<Lugar> listaOrdenada = new ArrayList<>();
        Bundle bundle = getIntent().getExtras();
        listaOrdenada= (ArrayList<Lugar>) bundle.getSerializable(getString(R.string.lugar));
        Collections.sort(listaOrdenada);
        Collections.reverse(listaOrdenada);
        for (int i=0; i<5; i++){
            listLugar.add(listaOrdenada.get(i));
        }
        listLugar=lugarService.atualizarNotaSlope(listLugar,this);
    }
}