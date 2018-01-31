package br.ufrpe.beela.lugar.negocio;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

import java.util.ArrayList;

import br.ufrpe.beela.lugar.dao.LugarDAO;
import br.ufrpe.beela.lugar.dominio.Lugar;
import br.ufrpe.beela.perfil.dao.PerfilDAO;
import br.ufrpe.beela.perfil.dominio.PerfilComida;
import br.ufrpe.beela.perfil.dominio.PerfilEsporte;
import br.ufrpe.beela.perfil.dominio.PerfilMusica;
import br.ufrpe.beela.perfil.dominio.PerfilUsuario;
import br.ufrpe.beela.perfil.dominio.PerfilLugar;

/**
 * Created by vidal on 18/01/2018.
 */

public class LugarService {
    private Intent mapa(double destinolatitude, double destinolongitude) {
        Intent googleMaps = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse("http://maps.google.com/maps?saddr=" + "" + "&daddr=" + destinolatitude + "," + destinolongitude));
        googleMaps.setComponent(new ComponentName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity"));
        return (googleMaps);
    }

    public Intent getMapa(double l, double lg) {
        return mapa(l, lg);
    }
}

