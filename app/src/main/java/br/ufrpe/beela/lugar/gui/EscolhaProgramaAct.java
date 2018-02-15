package br.ufrpe.beela.lugar.gui;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import br.ufrpe.beela.gui.R;
import br.ufrpe.beela.lugar.dominio.Lugar;
import br.ufrpe.beela.lugar.negocio.LugarService;
import br.ufrpe.beela.lugar.negocio.SlopeOne;
import br.ufrpe.beela.perfil.dominio.PerfilUsuario;
import br.ufrpe.beela.usuario.dao.PessoaDAO;
import br.ufrpe.beela.usuario.dao.UsuarioDAO;
import br.ufrpe.beela.usuario.dominio.Pessoa;
import br.ufrpe.beela.usuario.dominio.Usuario;
import br.ufrpe.beela.usuario.gui.ContatoAct;
import br.ufrpe.beela.usuario.gui.LoginAct;

public class EscolhaProgramaAct extends AppCompatActivity {
    private Pessoa pessoa = LoginAct.getPessoa();
    private LugarService lugarService = new LugarService();
    private PerfilUsuario perfilUsuario = LoginAct.getPessoa().getPerfilAtual();
    private ArrayList<Lugar> listaLugaresRecomendados = new ArrayList<Lugar>();
    private ImageButton botaoSozinho;
    private ImageButton botaoAcompanhado;


    private static ArrayList<Lugar> ListaLugar = new ArrayList<Lugar>();
    private static ArrayList<Pessoa> ListaPessoa = new ArrayList<Pessoa>();

    private ArrayList<Lugar> lugaresComMaioresNotas = new ArrayList<Lugar>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escolha_programa);
        encontrarLugarSozinho();
        encontratLugarAcompanhado();
    }

    private void encontrarLugarSozinho() {
        botaoSozinho = (ImageButton) findViewById(R.id.imageButton5);
        botaoSozinho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                gerarSozinho();
                //enviarListaDeRecomendados();
            }
        });
    }

    private void encontratLugarAcompanhado() {
        botaoAcompanhado = (ImageButton) findViewById(R.id.imageButton7);
        botaoAcompanhado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setListaPessoa(lugarService.gerarListaDePessoa(EscolhaProgramaAct.this));
                startActivity(new Intent(EscolhaProgramaAct.this, ContatoAct.class));
            }
        });
    }


    public void gerarSozinho() {
        startActivity(new Intent(EscolhaProgramaAct.this,ProgramaSozinhoAct.class));
        finish();
//        if(!lugarService.verificarJaVotou(pessoa.getId(),this)) {
//            ArrayList<Lugar> lugarArrayList = lugarService.gerarListaLugar(perfilUsuario, this);
//            EscolhaProgramaAct.setListaLugar(lugarArrayList);
//            getLugaresComMaiorNota(EscolhaProgramaAct.getListaLugar());
//        }
//        listaLugaresRecomendados = lugarService.atualizarNotaSlope(getRecomendacao(),this);
//  //      iniciarMatriz();
    }

    public static void setListaLugar(ArrayList<Lugar> listaLugar) {
        ListaLugar = listaLugar;
    }

    public static ArrayList<Lugar> getListaLugar() {

 //       return lugaresComMaioresNotas;
        return ListaLugar;
    }

    public static ArrayList<Pessoa> getListaPessoa() {
        return ListaPessoa;
    }

    public static void setListaPessoa(ArrayList<Pessoa> listaPessoa) {
        ListaPessoa = listaPessoa;
    }


    public ArrayList<Lugar> getLugaresRecomendados() {
        return listaLugaresRecomendados;
    }


    public ArrayList<Lugar> getRecomendacao(){
        if(!lugarService.verificarJaVotou(pessoa.getId(),this)) {
            return getListaLugaresQuandoNaoVotou();
        }
        else{
            return getListaLugaresQuandoVotou();
        }
    }


    public ArrayList<Lugar> getListaLugaresQuandoNaoVotou(){
        HashMap<Pessoa, HashMap<Lugar, Double>> matrizTotal = new HashMap<>();
// aqui retorna todos exceto quem está logado
        ArrayList<Pessoa> listaPessoas = new ArrayList<Pessoa>();
        listaPessoas=lugarService.gerarListaPessoaSistema(this);

        for (int i=0; i<listaPessoas.size(); i++){
            matrizTotal.put(listaPessoas.get(i) ,lugarService.getNotasPorPessoa(listaPessoas.get(i).getId(),this));
        }

        for (Lugar lugar : EscolhaProgramaAct.getListaLugar()) {
            HashMap<Lugar, Double> hashMap = new HashMap<>();
            hashMap.put(lugar, lugar.getNotaGeral());
            matrizTotal.put(pessoa, hashMap);
        }
// até aqui ok pegou todos e quem esta logado 19 matriztotal
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
            matrizTotal.put(listaTodasPessoas.get(i), lugarService.getNotasPorPessoa(listaTodasPessoas.get(i).getId(), this));
        }

        ArrayList<Lugar> listaLugares = lugarService.getListaLugares(this);
        SlopeOne slope=new SlopeOne(matrizTotal, listaLugares);
        slope.slopeOne();

        return slope.getListaRecomendados(pessoa);

    }


//ok 6 de 7 com as nota igual ou maior que a media
    public void getLugaresComMaiorNota(ArrayList<Lugar> listaLugar) {
        ArrayList<Lugar> lugarArrayList = new ArrayList<Lugar>();
        for (Lugar lugar : listaLugar) {
            if (lugar.getNotaGeral()>= 3.8) {
                lugarArrayList.add(lugar);
            }
        }
        EscolhaProgramaAct.setListaLugar(lugarArrayList);
    }
    public void enviarListaDeRecomendados(){
        Intent intent = new Intent(EscolhaProgramaAct.this, LugarAct.class);
        intent.putExtra(getString(R.string.lugar), listaLugaresRecomendados);
        startActivity(intent);
        finish();
    }
}
