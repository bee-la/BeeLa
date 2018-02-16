package br.ufrpe.beela.lugar.gui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import java.io.Serializable;
import java.util.ArrayList;
import br.ufrpe.beela.gui.R;
import br.ufrpe.beela.lugar.dominio.Lugar;
import br.ufrpe.beela.lugar.negocio.AdapterCustomizado;
import br.ufrpe.beela.lugar.negocio.LugarService;

/**
 * Created by max on 06/01/18.
 */

public class LugarAcompAct extends AppCompatActivity {
    private double destinolatitude;
    private double destinolongitude;
//    private static Lugar lugarSelecionado;
    private ArrayList<Lugar> listLugar = new ArrayList<Lugar>();
    private ListView listViewLugares;
    private LugarService mapa = new LugarService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lugares);
        setLugaresRecuperados();
        setListView();
    }

    public ArrayList<Lugar> getLugares() {
        return listLugar;
    }

    private void setListView() {
        listViewLugares = (ListView) findViewById(R.id.listView2);

        AdapterCustomizado max = new AdapterCustomizado(getApplicationContext(), getLugares());
        listViewLugares.setAdapter(max);
        listViewLugares.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Lugar LugarSelecionado = (Lugar) parent.getAdapter().getItem(position);
                irTelaDescricaoLugar(LugarSelecionado);
            }
        });
    }

    public void irTelaDescricaoLugar(Lugar lugar) {
//        lugarSelecionado = lugarzinho;
        Intent intent = new Intent(LugarAcompAct.this, LugarDetalhesAct.class);
        intent.putExtra(getString(R.string.lugar), (Serializable) lugar);
        startActivity(intent);

    }

    public void setLugaresRecuperados(){
        Bundle bundle = getIntent().getExtras();
        listLugar = (ArrayList<Lugar>) bundle.getSerializable(getString(R.string.lugar));
    }
}

