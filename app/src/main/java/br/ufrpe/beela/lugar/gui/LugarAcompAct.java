package br.ufrpe.beela.lugar.gui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import br.ufrpe.beela.gui.R;
import br.ufrpe.beela.lugar.dominio.Lugar;
import br.ufrpe.beela.lugar.negocio.AdapterCustomizado;
import br.ufrpe.beela.lugar.negocio.LugarService;
import br.ufrpe.beela.usuario.gui.ContatoAct;

/**
 * Created by max on 06/01/18.
 */

public class LugarAcompAct extends AppCompatActivity {
    private double destinolatitude;
    private double destinolongitude;
    private Button btIr;

    private ArrayList<Lugar> ListLugar = ContatoAct.getListaLugar();
    private ListView listViewLugares;

    Toast Erro;
    LugarService mapa = new LugarService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_lugares);
        LugarService mapa = new LugarService();
        setListView();


    }

    public ArrayList<Lugar> getLugares() {
        return ListLugar;
    }

    public Intent chamarMapa(Lugar lugar) {


        String destino[] = lugar.getLocalicao().split(",");
        destinolatitude = Double.parseDouble(destino[0]);
        destinolongitude = Double.parseDouble(destino[1]);

        try {
            startActivity(new Intent(mapa.getMapa(destinolatitude, destinolongitude)));
        } catch (Exception ex) {

            Toast.makeText(this, "ERRO MAPA", Toast.LENGTH_SHORT).show();
        }

        return mapa.getMapa(destinolatitude, destinolongitude);
    }


    private void setListView() {
        listViewLugares = (ListView) findViewById(R.id.listView2);

        AdapterCustomizado Max = new AdapterCustomizado(getApplicationContext(), getLugares());

        listViewLugares.setAdapter(Max);

        listViewLugares.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Lugar LugarSelecionado = (Lugar) parent.getAdapter().getItem(position);
                chamarMapa(LugarSelecionado);

            }
        });

    }
}