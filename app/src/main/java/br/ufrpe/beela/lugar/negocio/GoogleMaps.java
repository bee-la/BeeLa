package br.ufrpe.beela.lugar.negocio;

import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;
import br.ufrpe.beela.gui.R;
import br.ufrpe.beela.lugar.dominio.Lugar;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


/**
 * Created by Anderson on 23/01/2018.
 */

public class GoogleMaps extends AppCompatActivity {

    private double destinolatitude;
    private double destinolongitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lugares);

    }

    public void chamarMapa(Lugar lugar) {
        String destino[] = lugar.getLocalicao().split(",");
        destinolatitude = Double.parseDouble(destino[0]);
        destinolongitude = Double.parseDouble(destino[1]);;

        try {
            Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                    Uri.parse("http://maps.google.com/maps?saddr=&daddr=" + destinolatitude + "," + destinolongitude));

            intent.setComponent(new ComponentName(getString(R.string.comandoAppMaps), getString(R.string.comandoMapsActivity)));

            startActivity(intent);
        } catch (Exception ex) {
            ex.printStackTrace();
            Toast.makeText(this, R.string.erroNaoTemGoogleMaps, Toast.LENGTH_SHORT).show();
        }
    }

    private void verificarGPS() {
        //TODO: Ligar Gps
        String validarGPS = Settings.Secure.getString(getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
        if (validarGPS == null || validarGPS.length() == 0) {
            Toast erro;
            erro = Toast.makeText(getApplicationContext(), "ATIVE o Gps Porfavor", Toast.LENGTH_SHORT);
            erro.show();
            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivityForResult(intent, 1);
        }
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Permissão do Gps
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
            Toast erro;
            erro = Toast.makeText(getApplicationContext(), "Permissao Desativa", Toast.LENGTH_SHORT);
            erro.show();
            return;
        }
    }

}
