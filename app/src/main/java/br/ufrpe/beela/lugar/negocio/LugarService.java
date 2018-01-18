package br.ufrpe.beela.lugar.negocio;

import android.content.Context;

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

    public ArrayList<Lugar> sqlGetListLugar(PerfilUsuario perfilUsuario, Context context){
        ArrayList<Lugar>listLugar = new ArrayList<Lugar>();
        ArrayList<Integer>listId = new ArrayList<>();
        for (PerfilComida comida : perfilUsuario.getComida()){
            PerfilDAO bd = new PerfilDAO();
            bd.getLer(context);
            bd.sqlGetPerfilLugar(listId,comida);
        }
        for (PerfilMusica musica : perfilUsuario.getMusica()){
            PerfilDAO bd = new PerfilDAO();
            bd.getLer(context);
            bd.sqlGetPerfilLugar(listId,musica);
        }
        for (PerfilEsporte esporte : perfilUsuario.getEsporte()){
            PerfilDAO bd = new PerfilDAO();
            bd.getLer(context);
            bd.sqlGetPerfilLugar(listId,esporte);
        }
        for (int id : listId) {
            LugarDAO bd = new LugarDAO();
            bd.getLer(context);
            listLugar.add(bd.sqlGetLugar(id));
        }
        return listLugar;
    }

}

