package br.ufrpe.beela.lugar.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import java.util.ArrayList;
import br.ufrpe.beela.gui.R;
import br.ufrpe.beela.lugar.dominio.Lugar;
import br.ufrpe.beela.lugar.negocio.LugarService;
import br.ufrpe.beela.perfil.dominio.PerfilUsuario;
import br.ufrpe.beela.usuario.dominio.Pessoa;
import br.ufrpe.beela.usuario.gui.ContatoAct;
import br.ufrpe.beela.usuario.gui.LoginAct;

public class EscolhaProgramaAct extends AppCompatActivity {
    private LugarService lugarService = new LugarService();
    private PerfilUsuario perfilUsuario = LoginAct.getPessoa().getPerfilAtual();
    private ImageButton botaoSozinho;
    private ImageButton botaoAcompanhado;


    private static ArrayList<Lugar> ListaLugar = new ArrayList<Lugar>();
    private static ArrayList<Pessoa> ListaPessoa = new ArrayList<Pessoa>();

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
                startActivity(new Intent(EscolhaProgramaAct.this, LugarAct.class));
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
        ArrayList<Lugar> lugarArrayList = lugarService.gerarListaLugar(perfilUsuario, this);
        EscolhaProgramaAct.setListaLugar(lugarArrayList);
    }

    public static void setListaLugar(ArrayList<Lugar> listaLugar) {
        ListaLugar = listaLugar;
    }

    public static ArrayList<Lugar> getListaLugar() {
        return ListaLugar;
    }

    public static ArrayList<Pessoa> getListaPessoa() {
        return ListaPessoa;
    }

    public static void setListaPessoa(ArrayList<Pessoa> listaPessoa) {
        ListaPessoa = listaPessoa;
    }
}
