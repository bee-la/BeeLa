package br.ufrpe.beela.lugar.negocio;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

import br.ufrpe.beela.lugar.dao.LugarDAO;
import br.ufrpe.beela.lugar.dominio.Lugar;
import br.ufrpe.beela.perfil.dao.PerfilDAO;
import br.ufrpe.beela.perfil.dominio.PerfilComida;
import br.ufrpe.beela.perfil.dominio.PerfilEsporte;
import br.ufrpe.beela.perfil.dominio.PerfilMusica;
import br.ufrpe.beela.perfil.dominio.PerfilUsuario;

/**
 * Created by vidal on 18/01/2018.
 */

public class LugarService {

    public ArrayList<Lugar> getListaLugar(PerfilUsuario perfilUsuario, Context context){
        ArrayList<Lugar>listLugar = new ArrayList<Lugar>();
        ArrayList<Integer>listId = new ArrayList<>();

        try {
        for (PerfilComida comida : perfilUsuario.getComida()){
            PerfilDAO bd = new PerfilDAO();
            bd.getLer(context);
            bd.getPerfilLugar(listId,comida);
        }
        for (PerfilMusica musica : perfilUsuario.getMusica()){
            PerfilDAO bd = new PerfilDAO();
            bd.getLer(context);
            bd.getPerfilLugar(listId,musica);
        }

        for (PerfilEsporte esporte : perfilUsuario.getEsporte()){
            PerfilDAO bd = new PerfilDAO();
            bd.getLer(context);
            bd.getPerfilLugar(listId,esporte);
        }
        for (int id : listId) {
            LugarDAO bd = new LugarDAO();
            bd.getLer(context);
            Lugar lugar = bd.getLugar(id);
            PerfilDAO bdp = new PerfilDAO();
            bdp.getEscrever(context);
            lugar.setComida(bdp.getPerfilComida(lugar));
            bdp = new PerfilDAO();
            bdp.getEscrever(context);
            lugar.setEsporte(bdp.getPerfilEsporte(lugar));
            bdp = new PerfilDAO();
            bdp.getEscrever(context);
            lugar.setMusica(bdp.getPerfilMusica(lugar));
            listLugar.add(lugar);
        }
        }catch (Exception e){}
        return listLugar;
    }

}

