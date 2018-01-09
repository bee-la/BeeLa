package br.ufrpe.beela.lugar.negocio;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import br.ufrpe.beela.lugar.gui.LugaresAct;

/**
 * Created by vidal on 20/12/2017.
 */

public class lugarService extends AppCompatActivity {

    private double origemlatitude ;
    private double origemlongitude ;

    private double destinolatitude;
    private double destinolongitude ;



    private void Mapa() {
        origemlatitude = -8.0176527;
        origemlongitude = -34.9465626;

        destinolatitude = -8.014121;
        destinolongitude = -34.951131;

        try {
            Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                    Uri.parse("http://maps.google.com/maps?saddr=" + origemlatitude + "," + origemlongitude + "&daddr=" + destinolatitude + "," + destinolongitude));


            intent.setComponent(new ComponentName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity"));

            startActivity(intent);
        } catch (Exception ex) {
            ex.printStackTrace();
            Toast.makeText(getApplicationContext(), "Verifique se o Google Maps está instalado em seu dispositivo", Toast.LENGTH_SHORT).show();
        }
    }

    public void chamarMapa(){

        Mapa();

    }

}
