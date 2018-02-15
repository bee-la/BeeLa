package br.ufrpe.beela.lugar.gui;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import br.ufrpe.beela.gui.R;
import br.ufrpe.beela.lugar.dominio.Lugar;
import br.ufrpe.beela.lugar.negocio.AdapterCustomizado;

/**
 * Created by max on 06/01/18.
 */

public class LugarAct extends AppCompatActivity  {


    private ArrayList<Lugar> ListLugar = new ArrayList<Lugar>();
    private ListView listViewLugares;
    Button maps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLugarRecuperado();
        setContentView(R.layout.activity_lugares);
        verificarGPS();

        maps = (Button)findViewById(R.id.buttonIrMaps);
        maps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LugarAct.this,GoogleMapsActivity.class);
                intent.putExtra(getString(R.string.lugarMaps), (Serializable) ListLugar);
                startActivity(intent);
            }
        });
        setListView();
    }

    public ArrayList<Lugar> getLugares() {
        return ListLugar;
    }

    private void setListView() {
        listViewLugares = (ListView) findViewById(R.id.listView2);
        AdapterCustomizado adapter = new AdapterCustomizado(getApplicationContext(), getLugares());
        listViewLugares.setAdapter(adapter);
        listViewLugares.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Lugar LugarSelecionado = (Lugar) parent.getAdapter().getItem(position);
                irTelaDescricaoLugar(LugarSelecionado);


            }
        });

    }
    public void irTelaDescricaoLugar(Lugar lugarzinho) {
        Intent intent = new Intent(LugarAct.this,LugarDetalhesAct.class);
        intent.putExtra(getString(R.string.lugar), (Serializable) lugarzinho);
        startActivity(intent);
    }
    private void verificarGPS() {
        //TODO: Ligar Gps
        String validarGPS = Settings.Secure.getString(getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
        //Se vier null ou length == 0   é por que o Gps esta desabilitado.
        //Para abrir a tela do menu configuração e Ativar Gps!
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
    public void getLugarRecuperado(){
        Bundle bundle = getIntent().getExtras();
        ListLugar = (ArrayList<Lugar>) bundle.getSerializable(getString(R.string.lugar));
    }
}