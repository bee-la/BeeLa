package br.ufrpe.beela.lugar.gui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.content.ComponentName;
import android.net.Uri;
import android.widget.Toast;


import br.ufrpe.beela.gui.R;
import br.ufrpe.beela.lugar.negocio.lugarService;

/**
 * Created by max on 06/01/18.
 */

public class LugaresAct extends AppCompatActivity {

    //    private lugarService local = new lugarService();
    private Button btIr;
    private double origemlatitude;
    private double origemlongitude;
    private double destinolatitude;
    private double destinolongitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lugares_sugestao);

        btIr = findViewById(R.id.buttonIr);
//
        btIr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chamarMapa();
            }
        });
    }

    private void chamarMapa() {
        origemlatitude = -8.0176527;
        origemlongitude = -34.9465626;

        destinolatitude = -8.014121;
        destinolongitude = -34.951131;

        try {
            Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                    Uri.parse("http://maps.google.com/maps?saddr=" + origemlatitude + "," + origemlongitude + "&daddr=" + destinolatitude + "," + destinolongitude));


            intent.setComponent(new ComponentName(getString(R.string.comandoAppMaps), getString(R.string.comandoMapsActivity)));

            startActivity(intent);
        } catch (Exception ex) {
            Toast.makeText(this, R.string.erroNaoTemGoogleMaps, Toast.LENGTH_SHORT).show();
        }
    }
}