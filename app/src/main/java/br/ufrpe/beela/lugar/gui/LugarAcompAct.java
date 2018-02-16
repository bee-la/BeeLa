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
import br.ufrpe.beela.usuario.gui.ContatoAct;

/**
 * Created by max on 06/01/18.
 */

public class LugarAcompAct extends AppCompatActivity {

    private double destinolatitude;
    private double destinolongitude;
    private Button btIr;
    private static Lugar lugarSelecionado;
//    private ArrayList<Lugar> ListLugar = ContatoAct.getListaLugar();

    private ArrayList<Lugar> listaLugares = new ArrayList<Lugar>();
    private ListView listViewLugares;

    Toast Erro;
    LugarService mapa = new LugarService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_lugares);
        LugarService mapa = new LugarService();
        setLugaresRecuperados();
        setListView();


    }

    public ArrayList<Lugar> getLugares() {
        return listaLugares;
    }

    public Intent chamarMapa(Lugar lugar) {


        String destino[] = lugar.getLocalicao().split(",");
        destinolatitude = Double.parseDouble(destino[0]);
        destinolongitude = Double.parseDouble(destino[1]);

        try {
            startActivity(new Intent(mapa.getMapa(destinolatitude, destinolongitude)));
        } catch (Exception ex) {

            Toast.makeText(this, R.string.erroMapa, Toast.LENGTH_SHORT).show();
        }

        return mapa.getMapa(destinolatitude, destinolongitude);
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

    public void irTelaDescricaoLugar(Lugar lugarzinho) {
        lugarSelecionado = lugarzinho;
        Intent intent = new Intent(LugarAcompAct.this, LugarDetalhesAct.class);
        intent.putExtra(getString(R.string.lugar), (Serializable) lugarzinho);
        startActivity(intent);

    }

    public void setLugaresRecuperados(){
        Bundle bundle = getIntent().getExtras();
        listaLugares = (ArrayList<Lugar>) bundle.getSerializable(getString(R.string.lugar));
    }
}



//
//    Intent intent= new Intent(this, ActivityTelaPessoal.class);
//intent.putExtra("usuario", usuarioUsado);
//        startActivity(intent);
//
//        e na activity ActivityTelaPessoal você recupera seu objeto;

