package br.ufrpe.beela.lugar.gui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import java.io.Serializable;
import java.util.ArrayList;
import br.ufrpe.beela.gui.R;
import br.ufrpe.beela.lugar.dominio.Lugar;
import br.ufrpe.beela.lugar.negocio.AdapterCustomizado;

/**
 * Created by max on 06/01/18.
 */

public class LugarAct extends AppCompatActivity  {


    private ArrayList<Lugar> ListLugar = EscolhaProgramaAct.getListaLugar();
    private ListView listViewLugares;
    Button maps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_lugares);
        maps = (Button)findViewById(R.id.buttonIrMaps);
        maps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LugarAct.this,GoogleMapsActivity.class);
                intent.putExtra(getString(R.string.lugarMaps), (Serializable) ListLugar);
                startActivity(intent);
            }
        });
        setListView();
    }

    public ArrayList<Lugar> getLugares() {
        return ListLugar;
    }

    private void setListView() {
        listViewLugares = (ListView) findViewById(R.id.listView2);
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

}