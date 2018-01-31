package br.ufrpe.beela.lugar.gui;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import java.util.ArrayList;
import br.ufrpe.beela.gui.R;
import br.ufrpe.beela.lugar.dao.LugarDAO;
import br.ufrpe.beela.lugar.dominio.Lugar;
import br.ufrpe.beela.perfil.dao.PerfilDAO;
import br.ufrpe.beela.perfil.dominio.PerfilComida;
import br.ufrpe.beela.perfil.dominio.PerfilEsporte;
import br.ufrpe.beela.perfil.dominio.PerfilLugar;
import br.ufrpe.beela.perfil.dominio.PerfilMusica;
import br.ufrpe.beela.perfil.dominio.PerfilUsuario;
import br.ufrpe.beela.usuario.gui.LoginAct;


public class EscolhaProgramaAct extends AppCompatActivity {
    private PerfilUsuario perfilUsuario = LoginAct.getPessoa().getPerfilAtual();
    private ImageButton botaoSozinho;
    private static ArrayList<Lugar> ListaLugar = new ArrayList<Lugar>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escolha_programa);
        encontrarLugarSozinho();

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


    public ArrayList<Lugar> gerarListaLugar(PerfilUsuario perfilUsuario, Context context) {
        ArrayList<Lugar> listLugar = new ArrayList<Lugar>();
        ArrayList<Integer> listId = new ArrayList<Integer>();

        try {
            for (PerfilComida comida : perfilUsuario.getComida()) {
                PerfilDAO bd = new PerfilDAO();
                bd.getLer(context);
                listId = bd.getPerfilParaLugar(comida);
            }
            for (PerfilMusica musica : perfilUsuario.getMusica()) {
                PerfilDAO bd = new PerfilDAO();
                bd.getLer(context);
                listId = bd.getPerfilParaLugar(listId, musica);
            }
            for (PerfilEsporte esporte : perfilUsuario.getEsporte()) {
                PerfilDAO bd = new PerfilDAO();
                bd.getLer(context);
                listId = bd.getPerfilParaLugar(listId, esporte);
            }
            for (PerfilLugar lugar : perfilUsuario.getLugar()) {
                PerfilDAO bd = new PerfilDAO();
                bd.getLer(context);
                listId = bd.getPerfilParaLugar(listId, lugar);
            }
        } catch (Exception e) {}
        for (int id : listId) {
            LugarDAO bd = new LugarDAO();
            bd.getLer(context);
            Lugar lugar = bd.getLugar(id);
            listLugar.add(lugar);
        }
        return listLugar;
    }

    public void gerarSozinho() {
        ArrayList<Lugar> lugarArrayList = gerarListaLugar(perfilUsuario, this);
        EscolhaProgramaAct.setListaLugar(lugarArrayList);
    }

    public static void setListaLugar(ArrayList<Lugar> listaLugar) {
        ListaLugar = listaLugar;
    }

    public static ArrayList<Lugar> getListaLugar() {
        return ListaLugar;
    }


}
