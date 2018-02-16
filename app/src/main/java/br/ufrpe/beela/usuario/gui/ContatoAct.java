package br.ufrpe.beela.usuario.gui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.HashMap;
import br.ufrpe.beela.gui.R;
import br.ufrpe.beela.lugar.dominio.Lugar;
import br.ufrpe.beela.lugar.gui.EscolhaProgramaAct;
import br.ufrpe.beela.lugar.gui.LugarAct;
import br.ufrpe.beela.lugar.negocio.LugarService;
import br.ufrpe.beela.lugar.negocio.SlopeOne;
import br.ufrpe.beela.usuario.dominio.Pessoa;
import br.ufrpe.beela.usuario.negocio.ListViewContato;

/**
 * Created by Anderson on 20/01/2018.
 */

public class ContatoAct extends AppCompatActivity {
    private ListView listViewContatos;
    private Pessoa pessoa = LoginAct.getPessoa();
    private ArrayList<Pessoa> pessoaArrayList = EscolhaProgramaAct.getListaPessoa();
    private ArrayList<Lugar> lugarArrayList = new ArrayList<Lugar>();
    private LugarService lugarService = new LugarService();
    private ArrayList<Lugar> listaFiltradaPorNota = new ArrayList<>();
    private ArrayList<Lugar> listaLugaresGrupo = new ArrayList<Lugar>();

    private Button botaoConfirmar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contatos);
        setListView();
        clicarBotaoConfirmar();
    }

    public ArrayList<Pessoa> getContatos() {
        return pessoaArrayList;
    }

    public void setListView() {
        listViewContatos = (ListView) findViewById(R.id.listView3);
        ListViewContato lista = new ListViewContato(ContatoAct.this);
        listViewContatos.setAdapter(lista);
    }

    private void clicarBotaoConfirmar() {

        botaoConfirmar = (Button) findViewById(R.id.button5);
        botaoConfirmar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                verificaSelecionados();
                filtrarPorNota(lugarArrayList);
                listaLugaresGrupo = lugarService.atualizarNotaSlope(getRecomendacao(),ContatoAct.this);
                enviarLugaresLugarAct();
            }
        });
    }

    private void verificaSelecionados() {
        ArrayList<Pessoa> pessoaArrayList = new ArrayList<Pessoa>();
        try {
            if (listViewContatos != null) {
                Adapter adapter = (Adapter) listViewContatos.getAdapter();
                for (int i = 0; i < adapter.getCount(); i++) {
                    Pessoa pessoa = (Pessoa) adapter.getItem(i);
                    if (pessoa.isSelecionado()) {
                        pessoaArrayList.add(pessoa);
                    }
                }
            }
            pessoaArrayList.add(pessoa);

            ArrayList<Pessoa> listaPessoaObj=lugarService.gerarPerfisPessoasAcompanhado(pessoaArrayList, this);

            setListaLugar(lugarService.gerarLugarAcompanhado(listaPessoaObj,this));
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), getString(R.string.erro), Toast.LENGTH_SHORT).show();
        }
    }
    public void setListaLugar(ArrayList<Lugar> listaLugar){
        lugarArrayList = listaLugar;
    }


    public void filtrarPorNota(ArrayList<Lugar> lugares){
        for (Lugar lugar : lugares){
            if(lugar.getNotaGeral()>= 3.8){
                listaFiltradaPorNota.add(lugar);
            }
        }
    }

    public ArrayList<Lugar> getRecomendacao(){
        HashMap<Pessoa, HashMap<Lugar, Double>> matrizTotal = new HashMap<>();
        ArrayList<Pessoa> listaPessoas = new ArrayList<Pessoa>();

        listaPessoas=lugarService.gerarListaPessoaSistema(this);

        for (int i=0; i<listaPessoas.size(); i++){
            matrizTotal.put(listaPessoas.get(i) ,
                    lugarService.getNotasPorPessoa(listaPessoas.get(i).getId(),this));
        }

        for (Lugar lugar : listaFiltradaPorNota) {
            HashMap<Lugar, Double> hashMap = new HashMap<>();
            hashMap.put(lugar, lugar.getNotaGeral());
            matrizTotal.put(pessoa, hashMap);
        }
        ArrayList<Lugar> listaLugares = lugarService.getListaLugares(this);

        SlopeOne slope=new SlopeOne(matrizTotal, listaLugares);
        slope.slopeOne();
        return slope.getListaRecomendados(pessoa);
    }

    public void enviarLugaresLugarAct(){
        Intent intent = new Intent(ContatoAct.this, LugarAct.class);
        intent.putExtra(getString(R.string.lugar), listaLugaresGrupo);
        startActivity(intent);
        finish();
    }
}
