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

    private ArrayList<Lugar> listaLugaresRecomendados = new ArrayList<Lugar>();
    private ImageButton botaoSozinho;
    private ImageButton botaoAcompanhado;

    private static ArrayList<Pessoa> listaPessoa = new ArrayList<Pessoa>();
    private LugarService lugarService = new LugarService();

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
            }
        });
    }

    private void encontratLugarAcompanhado() {
        botaoAcompanhado = (ImageButton) findViewById(R.id.imageButton7);
        botaoAcompanhado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setListaPessoa(lugarService.gerarListaDePessoa(EscolhaProgramaAct.this));
                gerarAcompanhado();
            }
        });
    }


    public void gerarSozinho() {
        startActivity(new Intent(EscolhaProgramaAct.this,ProgramaSozinhoAct.class));
        finish();
    }
    public void gerarAcompanhado() {
        startActivity(new Intent(EscolhaProgramaAct.this, ContatoAct.class));
        finish();
    }


//    public static void setListaLugar(ArrayList<Lugar> listaLugar) {
//        ListaLugar = listaLugar;
//    }
//
//    public static ArrayList<Lugar> getListaLugar() {
//        return ListaLugar;
//    }

    public static ArrayList<Pessoa> getListaPessoa() {
        return listaPessoa;
    }

    public static void setListaPessoa(ArrayList<Pessoa> lista) {
        listaPessoa = lista;
    }
}
