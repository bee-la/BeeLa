package br.ufrpe.beela.lugar.negocio;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

import br.ufrpe.beela.gui.R;
import br.ufrpe.beela.lugar.dao.LugarDAO;
import br.ufrpe.beela.lugar.dominio.Lugar;
import br.ufrpe.beela.lugar.gui.LugarAct;
import br.ufrpe.beela.perfil.dao.PerfilDAO;
import br.ufrpe.beela.perfil.dominio.PerfilComida;
import br.ufrpe.beela.perfil.dominio.PerfilEsporte;
import br.ufrpe.beela.perfil.dominio.PerfilMusica;
import br.ufrpe.beela.perfil.dominio.PerfilUsuario;

/**
 * Created by vidal on 18/01/2018.
 */

public class LugarService {
    Toast Erro;
    public ArrayList<Lugar> getListaLugar(PerfilUsuario perfilUsuario,Context context){
        ArrayList<Lugar>listLugar = new ArrayList<Lugar>();
        ArrayList<Integer>listId = new ArrayList<Integer>();

        try {
            for (PerfilComida comida : perfilUsuario.getComida()) {
                PerfilDAO bd = new PerfilDAO();
                bd.getLer(context);
                listId = bd.getPerfilLugar(comida);
            }
            for (PerfilMusica musica : perfilUsuario.getMusica()) {
                PerfilDAO bd = new PerfilDAO();
                bd.getLer(context);
                listId = bd.getPerfilLugar(listId, musica);
            }

            for (PerfilEsporte esporte : perfilUsuario.getEsporte()) {
                PerfilDAO bd = new PerfilDAO();
                bd.getLer(context);
                listId = bd.getPerfilLugar(listId, esporte);
            }
        }
        catch (Exception e){}
        for (int id : listId) {
            LugarDAO bd = new LugarDAO();
            bd.getLer(context);
            Lugar lugar =  new Lugar();
            lugar = bd.getLugar(id);
//            PerfilDAO bdp = new PerfilDAO();
//            bdp.getEscrever(context);
//            lugar.setComida(bdp.getPerfilComida(lugar));
//            bdp = new PerfilDAO();
//            bdp.getEscrever(context);
//            lugar.setEsporte(bdp.getPerfilEsporte(lugar));
//            bdp = new PerfilDAO();
//            bdp.getEscrever(context);
//            lugar.setMusica(bdp.getPerfilMusica(lugar));
            listLugar.add(lugar);
        }
        return listLugar;
    }



    private Intent mapa(double destinolatitude, double destinolongitude) {


        Intent googleMaps = new Intent(android.content.Intent.ACTION_VIEW,
                Uri.parse("http://maps.google.com/maps?saddr=" + "" + "&daddr=" + destinolatitude + "," + destinolongitude));

        googleMaps.setComponent(new ComponentName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity"));
        return (googleMaps);


    }


    public Intent getMapa(double l, double lg) {



        return mapa(l, lg);
    }







}

