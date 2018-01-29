package br.ufrpe.beela.perfil.negocio;

import android.content.Context;
import android.util.Log;
import android.widget.CheckBox;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.ufrpe.beela.gui.R;
import br.ufrpe.beela.perfil.dao.PerfilDAO;
import br.ufrpe.beela.perfil.dominio.PerfilComida;
import br.ufrpe.beela.perfil.dominio.PerfilEsporte;
import br.ufrpe.beela.perfil.dominio.PerfilMusica;
import br.ufrpe.beela.perfil.dominio.PerfilUsuario;
import br.ufrpe.beela.perfil.dominio.PerfilLugar;
import br.ufrpe.beela.perfil.gui.PerfilAct;

/**
 * Created by vidal on 14/12/2017.
 */

public class PerfilService {


    public void adcComida(PerfilUsuario perfilUsuario, Context context){
        for (PerfilComida comida : perfilUsuario.getComida()){
            PerfilDAO bd = new PerfilDAO();
            bd.getEscrever(context);
            comida.setNome_perfil(perfilUsuario.getNome());
            bd.inserirPerfilComida(comida);
        }
    }

    public void adcMusica(PerfilUsuario perfilUsuario, Context context){
        for (PerfilMusica musica : perfilUsuario.getMusica()){
            PerfilDAO bd = new PerfilDAO();
            bd.getEscrever(context);
            musica.setNome_perfil(perfilUsuario.getNome());
            bd.inserirPerfilMusica(musica);
        }
    }
    public void adcLugar(PerfilUsuario perfilUsuario, Context context){
        for (PerfilLugar lugar : perfilUsuario.getLugar()){
            PerfilDAO bd = new PerfilDAO();
            bd.getEscrever(context);
            lugar.setNome_perfil(perfilUsuario.getNome());
            bd.inserirPerfilLugar(lugar);
        }
    }
    public void adcEsporte(PerfilUsuario perfilUsuario, Context context){
        for (PerfilEsporte esporte : perfilUsuario.getEsporte()){
            PerfilDAO bd = new PerfilDAO();
            bd.getEscrever(context);
            esporte.setNome_perfil(perfilUsuario.getNome());
            bd.inserirPerfilEsporte(esporte);}
    }
    public void adcPerfil(PerfilUsuario perfilUsuario, Context context){
        PerfilDAO bd = new PerfilDAO();
        bd.getEscrever(context);
        bd.inserirPerfil(perfilUsuario);
    }
    public void adcPerfilFavorito(int id,String nome, Context context){
        PerfilDAO bd = new PerfilDAO();
        bd.getEscrever(context);
        bd.inserirPerfilFavorito(id,nome);
    }

    public void excluirDoBanco(int Id,String nomePerfil,Context context){
        PerfilDAO bd = new PerfilDAO();
        bd.getEscrever(context);
        bd.deletePerfilUsuario(Id, nomePerfil);

    }
    public ArrayList<PerfilUsuario> MontarPerfis(PerfilUsuario perfilUsuario,Context context){
        PerfilDAO bd = new PerfilDAO();
        bd.getLer(context);
        return bd.getPerfil(perfilUsuario.getId_Usuario());

    }


}
