package br.ufrpe.beela.usuario.gui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

import br.ufrpe.beela.gui.R;
import br.ufrpe.beela.lugar.dao.LugarDAO;
import br.ufrpe.beela.lugar.dominio.Lugar;
import br.ufrpe.beela.lugar.gui.EscolhaProgramaAct;
import br.ufrpe.beela.lugar.gui.LugarAcompAct;
import br.ufrpe.beela.lugar.gui.LugarAct;
import br.ufrpe.beela.lugar.negocio.LugarService;
import br.ufrpe.beela.lugar.negocio.SlopeOne;
import br.ufrpe.beela.perfil.dao.PerfilDAO;
import br.ufrpe.beela.perfil.dominio.PerfilComida;
import br.ufrpe.beela.perfil.dominio.PerfilEsporte;
import br.ufrpe.beela.perfil.dominio.PerfilMusica;
import br.ufrpe.beela.perfil.dominio.PerfilUsuario;
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


    private ArrayList<Pessoa> listaPessoas = new ArrayList<Pessoa>();
    private PerfilUsuario perfilUsuario = LoginAct.getPessoa().getPerfilAtual();


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
//                irTelaLugarAct();
                enviarLugaresLugarAct();

            }
        });
    }

    public void exibirToast(TextView texto) {
        Toast.makeText(getApplicationContext(), texto.getText().toString(), Toast.LENGTH_SHORT).show();
    }
    public void irTelaLugarAct(){
        startActivity(new Intent(ContatoAct.this, LugarAcompAct.class));
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
//                        listaPessoas.add(pessoaLista);
                    }
                }
            }
            pessoaArrayList.add(pessoa);
//            listaPessoas.add(pessoa);

//            HashMap<Pessoa, HashMap<Lugar, Double>> matriz = new HashMap<>();
//            matriz=
//            listaLugaresGrupo=lugarService.atualizarNotaSlope(getLugaresEmGrupo(getMatrizTotal()), this);

            setListaLugar(lugarService.gerarLugarAcompanhado(pessoaArrayList,this));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void setListaLugar(ArrayList<Lugar> listaLugar){
        lugarArrayList = listaLugar;
    }

    public ArrayList<Lugar> getListaLugar(){
        return lugarArrayList;
    }

    public void filtrarPorNota(ArrayList<Lugar> lugares){
        for (Lugar lugar : lugares){
            if(lugar.getNotaGeral()>= 3.8){
                listaFiltradaPorNota.add(lugar);
            }
        }
        // Após verificação
//        listaLugar = listaFiltrada;
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











    //    public HashMap<Pessoa, HashMap<Lugar, Double>> getMatrizTotal(){
//        HashMap<Pessoa, HashMap<Lugar, Double>> matrizTotal = new HashMap<>();
//
//        for (Pessoa pessoaLista : listaPessoas){
//            //Se não votou
//            if(!lugarService.verificarJaVotou(pessoa.getId(),this)) {
//                ArrayList<Lugar> listaLugarBruto = new ArrayList<Lugar>();
//                listaLugarBruto = lugarService.gerarListaLugar(perfilUsuario, this);
//
//                for (Lugar lugar : listaLugarBruto){
//                    HashMap<Lugar, Double> hashMap = new HashMap<>();
//                    //Verifica se o lugar tem nota mínima e já adiciona no hashMap
//                    if (lugar.getNotaGeral() >= 3.8){
//                        hashMap.put(lugar, lugar.getNotaGeral());
//                        matrizTotal.put(pessoaLista, hashMap);
//                    }
//                }
//            }
//
//            //Se já votou
//            else{
//                matrizTotal.put(pessoaLista, lugarService.getNotasPorPessoa(pessoaLista.getId(), this));
//            }
//        }
//
//        return matrizTotal;
//    }
//
//    public ArrayList<Lugar> getLugaresEmGrupo(HashMap<Pessoa, HashMap<Lugar, Double>> matriz){
//        ArrayList<Lugar> listaLugares = lugarService.getListaLugares(this);
//        SlopeOne slope = new SlopeOne(matriz, listaLugares);
//        slope.slopeOne();
//        return slope.getListaRecomendados(pessoa);
//    }
//
    public void enviarLugaresLugarAct(){
        Intent intent = new Intent(ContatoAct.this, LugarAct.class);
        intent.putExtra(getString(R.string.lugar), listaLugaresGrupo);
        startActivity(intent);
        finish();
    }
}
