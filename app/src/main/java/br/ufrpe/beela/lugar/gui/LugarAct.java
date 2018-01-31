package br.ufrpe.beela.lugar.gui;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
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
    private double destinolatitude;
    private double destinolongitude;
    private Button btIr;

    public static Lugar getLugarhahah() {
        return lugarhahah;
    }

    private static Lugar lugarhahah;
    private ArrayList<Lugar> ListLugar = EscolhaProgramaAct.getListaLugar();
    //private BancoDeDados bancoTeste=new BancoDeDados();
//    private LugarService lugarService = new LugarService();
//    private PerfilUsuario perfilAtual= LoginAct.getPessoa().getPerfil().getPerfilAtual();
//    private PerfilUsuario perfilUsuario = LoginAct.getPessoa().getPerfil();
    private ListView listViewLugares;

    Toast Erro;
    //LugarService mapa = new LugarService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_lugares);
        LugarService mapa = new LugarService();
//        setContentView(R.layout.activity_lugares_sugestao);
         setListView();


    }

    public ArrayList<Lugar> getLugares(){
//        LugarDAO bdl = new LugarDAO();
//        bdl.getLer(this);
//        return bdl.gerarListaLugar();
        return ListLugar;
//       return lugarService.gerarListaLugar(perfilUsuario,this);
       //return bancoTeste.getLugaresPreferidos(this);
    }
//@TODO Falta tirar esse ListView desta Class e por na sua proprio !!!

//    public Intent chamarMapa(Lugar lugar) {
//
//
//
//        String destino[] = lugar.getLocalicao().split(",");
//        destinolatitude = Double.parseDouble(destino[0]);
//        destinolongitude = Double.parseDouble(destino[1]);
//
//        try {
//            startActivity(new Intent (mapa.getMapa(destinolatitude,destinolongitude)));
//        } catch (Exception ex){
//
//            Toast.makeText(this ,"Erro",Toast.LENGTH_SHORT).show();
//        }
//
//        return mapa.getMapa(destinolatitude,destinolongitude);
//    }


    private void setListView(){
        listViewLugares = (ListView) findViewById(R.id.listView2);

        //ListViewLugar lista = new ListViewLugar(LugarAct.this, listViewLugares);

        AdapterCustomizado Max = new AdapterCustomizado(getApplicationContext(),getLugares());

        listViewLugares.setAdapter(Max);

        listViewLugares.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Lugar lugarzinho = (Lugar) parent.getAdapter().getItem(position);
                irTelaDescricaoLugar(lugarzinho);
                //chamarMapa(lugarzinho);
                //startActivity(lugarAct, lugarAct.chamarMapa(lugarzinho),null );

                //Toast.makeText(ctx,lugarzinho.getLocalicao(),Toast.LENGTH_SHORT).show();
            }
        });




    }

    public void irTelaDescricaoLugar(Lugar lugarzinho) {
        lugarhahah = lugarzinho;
        Intent intent = new Intent(LugarAct.this,LugarDetalhes.class);


        startActivity(intent);


    }


}