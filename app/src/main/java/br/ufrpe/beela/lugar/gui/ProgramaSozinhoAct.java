package br.ufrpe.beela.lugar.gui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.HashMap;

import br.ufrpe.beela.gui.R;
import br.ufrpe.beela.lugar.dominio.Lugar;
import br.ufrpe.beela.lugar.negocio.LugarService;
import br.ufrpe.beela.lugar.negocio.SlopeOne;
import br.ufrpe.beela.perfil.dominio.PerfilUsuario;
import br.ufrpe.beela.usuario.dominio.Pessoa;
import br.ufrpe.beela.usuario.gui.ContatoAct;
import br.ufrpe.beela.usuario.gui.LoginAct;

public class ProgramaSozinhoAct extends AppCompatActivity {

    private Pessoa pessoa = LoginAct.getPessoa();
    private LugarService lugarService = new LugarService();
    private PerfilUsuario perfilUsuario = LoginAct.getPessoa().getPerfilAtual();
    private ArrayList<Lugar> listaLugaresRecomendados = new ArrayList<Lugar>();

    private  ArrayList<Lugar> listaLugar = new ArrayList<Lugar>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escolha_programa);

    gerarSozinho();
    }
    public void gerarSozinho() {
        if(!lugarService.verificarJaVotou(pessoa.getId(),this)) {
            // Pelo perfil retorna os lugares de forma bruta
            ArrayList<Lugar> lugarArrayList = lugarService.gerarListaLugar(perfilUsuario, this);
            listaLugar = lugarArrayList;
            // FOI FILTRADA
            setFiltraPorNota(listaLugar);
        }

        listaLugaresRecomendados = lugarService.atualizarNotaSlope(getRecomendacao(),this);
        enviarListaDeRecomendados();
    }
    public ArrayList<Lugar> getRecomendacao(){
       if(!lugarService.verificarJaVotou(pessoa.getId(),this)) {
           return getListaLugaresQuandoNaoVotou();
       }
        else{
            return getListaLugaresQuandoVotou();
        }
    }

    public void enviarListaDeRecomendados(){
        Intent intent = new Intent(ProgramaSozinhoAct.this, LugarAct.class);
        intent.putExtra(getString(R.string.lugar), listaLugaresRecomendados);
        startActivity(intent);
        finish();
    }
    public void setFiltraPorNota(ArrayList<Lugar> listaBruta){
        ArrayList<Lugar> listaFiltrada = new ArrayList<Lugar>();
        for (Lugar lugar:listaBruta){
            if(lugar.getNotaGeral()>= 3.8){
                listaFiltrada.add(lugar);
            }
        }
        // Após verificação
        listaLugar = listaFiltrada;
    }
    public ArrayList<Lugar> getListaLugaresQuandoNaoVotou(){
        HashMap<Pessoa, HashMap<Lugar, Double>> matrizTotal = new HashMap<>();
        ArrayList<Pessoa> listaPessoas = new ArrayList<Pessoa>();

        listaPessoas=lugarService.gerarListaPessoaSistema(this);

        for (int i=0; i<listaPessoas.size(); i++){
            matrizTotal.put(listaPessoas.get(i) ,
                    lugarService.getNotasPorPessoa(listaPessoas.get(i).getId(),this));
        }

        for (Lugar lugar : listaLugar) {
            HashMap<Lugar, Double> hashMap = new HashMap<>();
            hashMap.put(lugar, lugar.getNotaGeral());
            matrizTotal.put(pessoa, hashMap);
        }
        ArrayList<Lugar> listaLugares = lugarService.getListaLugares(this);

        SlopeOne slope=new SlopeOne(matrizTotal, listaLugares);
        slope.slopeOne();
        return slope.getListaRecomendados(pessoa);
    }

    public ArrayList<Lugar> getListaLugaresQuandoVotou(){
        HashMap<Pessoa, HashMap<Lugar, Double>> matrizTotal = new HashMap<>();

        ArrayList<Pessoa> listaTodasPessoas = new ArrayList<Pessoa>();
        listaTodasPessoas=lugarService.gerarListaTodasPessoa(this);

        for (int i=0; i<listaTodasPessoas.size(); i++){
            if(listaTodasPessoas.get(i).getId() == pessoa.getId()){matrizTotal.put(pessoa, lugarService.getNotasPorPessoa(pessoa.getId(), this));}
            else{matrizTotal.put(listaTodasPessoas.get(i), lugarService.getNotasPorPessoa(listaTodasPessoas.get(i).getId(), this));}
        }
        ArrayList<Lugar> listaLugares = lugarService.getListaLugares(this);
        SlopeOne slope=new SlopeOne(matrizTotal, listaLugares);
        slope.slopeOne();

        return slope.getListaRecomendados(pessoa);

    }
}
