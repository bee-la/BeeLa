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
    private static ArrayList<Pessoa> pessoaArrayList = new ArrayList<Pessoa>();
    private ArrayList<Lugar> lugarArrayList = new ArrayList<Lugar>();
    private LugarService lugarService = new LugarService();


    private ArrayList<Pessoa> listaPessoas = new ArrayList<Pessoa>();
    private PerfilUsuario perfilUsuario = LoginAct.getPessoa().getPerfilAtual();

    private ArrayList<Lugar> listaLugaresGrupo = new ArrayList<Lugar>();

    private Button botaoConfirmar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contatos);
        getPessoas();
        setListView();
        clicarBotaoConfirmar();
    }

    public static ArrayList<Pessoa> getContatos() {
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
                enviarLugaresLugarAcompAct(listaLugaresGrupo);
//                irTelaLugarAct();

            }
        });
    }

    private void verificaSelecionados() {
        try {
            if (listViewContatos != null) {
                Adapter adapter = (Adapter) listViewContatos.getAdapter();
                for (int i = 0; i < adapter.getCount(); i++) {
                    Pessoa pessoaLista = (Pessoa) adapter.getItem(i);
                    if (pessoaLista.isSelecionado()) {
//                        pessoaArrayList.add(pessoa);
                        listaPessoas.add(pessoaLista);
                    }
                }
            }
//            pessoaArrayList.add(pessoa);
            listaPessoas.add(pessoa);
            setLugaresEmGrupo();

//            ContatoAct.setListaLugar(lugarService.gerarLugarAcompanhado(pessoaArrayList,this));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setLugaresEmGrupo(){

        HashMap<Pessoa, HashMap<Lugar, Double>> matriz = new HashMap<>();
        matriz=getMatrizTotal();
        ArrayList<Lugar> lugares = new ArrayList<Lugar>();
        lugares=getLugaresEmGrupo(matriz);
        listaLugaresGrupo=lugarService.atualizarNotaSlope(lugares, this);
    }
    public HashMap<Pessoa, HashMap<Lugar, Double>> getMatrizTotal(){
        HashMap<Pessoa, HashMap<Lugar, Double>> matrizTotal = new HashMap<>();

        for (Pessoa pessoaLista : listaPessoas){
            //Se não votou
            if(!lugarService.verificarJaVotou(pessoaLista.getId(),this)) {
                ArrayList<Lugar> listaLugarBruto = new ArrayList<Lugar>();
                listaLugarBruto = lugarService.gerarListaLugar(pessoaLista.getPerfilAtual(), this);

                for (Lugar lugar : listaLugarBruto){
                    HashMap<Lugar, Double> hashMap = new HashMap<>();
                    //Verifica se o lugar tem nota mínima e já adiciona no hashMap
                    if (lugar.getNotaGeral() >= 3.8){
                        hashMap.put(lugar, lugar.getNotaGeral());
                        matrizTotal.put(pessoaLista, hashMap);
                    }
                }
            }

            //Se já votou
            else{
                matrizTotal.put(pessoaLista, lugarService.getNotasPorPessoa(pessoaLista.getId(), this));
            }
        }

        return matrizTotal;
    }

    public ArrayList<Lugar> getLugaresEmGrupo(HashMap<Pessoa, HashMap<Lugar, Double>> matriz){
        ArrayList<Lugar> listaLugares = lugarService.getListaLugares(this);
        SlopeOne slope = new SlopeOne(matriz, listaLugares);
        slope.slopeOne();
        //TODO ta errado deveria buscar para cada pessoa
        return slope.getListaRecomendados(listaPessoas.get(0));
    }

    public void enviarLugaresLugarAcompAct(ArrayList<Lugar> lugares){
        Intent intent = new Intent(ContatoAct.this, LugarAcompAct.class);
        intent.putExtra(getString(R.string.lugar), lugares);
        startActivity(intent);
        finish();

    }
    public void getPessoas(){
        ArrayList<Pessoa> pessoaArrayListAcomp = lugarService.gerarListaDePessoa(this);
        pessoaArrayList = lugarService.gerarPerfisPessoasAcompanhado(pessoaArrayListAcomp,this);
    }
}
