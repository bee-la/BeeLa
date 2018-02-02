package br.ufrpe.beela.lugar.gui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;


import java.io.Serializable;
import java.util.ArrayList;

import br.ufrpe.beela.gui.R;
import br.ufrpe.beela.lugar.dominio.Lugar;
import br.ufrpe.beela.lugar.negocio.AdapterCustomizado;
import br.ufrpe.beela.lugar.negocio.LugarService;

/**
 * Created by max on 06/01/18.
 */

public class LugarAct extends AppCompatActivity implements Serializable {

    private static Lugar lugarSelecionado;

    private ArrayList<Lugar> ListLugar = EscolhaProgramaAct.getListaLugar();

    private ListView listViewLugares;



    public static Lugar getLugarSelecionado() {
        return lugarSelecionado;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_lugares);

         setListView();


    }

    public ArrayList<Lugar> getLugares(){
           return ListLugar;
    }
//@TODO Falta tirar esse ListView desta Class e por na sua proprio !!!



    private void setListView(){
        listViewLugares = (ListView) findViewById(R.id.listView2);


        AdapterCustomizado adapter = new AdapterCustomizado(getApplicationContext(),getLugares());

        listViewLugares.setAdapter(adapter);

        listViewLugares.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Lugar lugarzinho = (Lugar) parent.getAdapter().getItem(position);
                irTelaDescricaoLugar(lugarzinho);

            }
        });


    }

    public void irTelaDescricaoLugar(Lugar lugarzinho) {
        lugarSelecionado = lugarzinho;
        Intent intent = new Intent(LugarAct.this,LugarDetalhes.class);

        startActivity(intent);


    }


}