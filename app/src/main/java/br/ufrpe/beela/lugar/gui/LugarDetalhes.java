package br.ufrpe.beela.lugar.gui;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.io.Serializable;
import br.ufrpe.beela.gui.R;
import br.ufrpe.beela.lugar.dominio.Lugar;
import br.ufrpe.beela.lugar.negocio.LugarService;

/**
 * Created by max on 30/01/18.
 */



public class LugarDetalhes extends AppCompatActivity  implements Serializable, View.OnClickListener {

    LugarAct a = new LugarAct();
    ImageButton localizacaoBt, siteBt;

    TextView nome,descricao;
    Lugar recuperado;
    ImageView foto;

    private double destinolatitude;
    private double destinolongitude;

    LugarService mapa = new LugarService();
    @Override
    protected void onCreate(Bundle saveInstanceState){

        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_lugar_detalhes);
        recuperado = a.getLugarSelecionado();
        setarDetalhes();

    }

    private void setarDetalhes() {
        nome = (TextView)findViewById(R.id.nomeDoLugar);
        nome.setText(recuperado.getNome().toString());
        descricao = findViewById(R.id.decriçãoLugar);
        descricao.setText(recuperado.getDescricao().toString());

        localizacaoBt = (ImageButton) findViewById(R.id.imageButtonLocalizacao);
        localizacaoBt.setOnClickListener(this);
        siteBt = (ImageButton) findViewById(R.id.imageButtonSite);
        siteBt.setOnClickListener(this);

        foto = (ImageView)findViewById(R.id.imgViewLugarFoto);

        String a =  recuperado.getCaminho();

        int id = getResources().getIdentifier(a,"drawable",getPackageName());

        foto.setImageResource(id);
    }

    public void onClick(View view){

        switch (view.getId()){
            case R.id.imageButtonLocalizacao:
                chamarMapa(recuperado);
                break;

            case R.id.imageButtonSite:
                //Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://google.com"));

                Intent intent = new Intent(Intent.ACTION_SEARCH);
                intent.putExtra(SearchManager.QUERY , recuperado.getNome().toString());
                if (intent.resolveActivity(getPackageManager()) != null){
                    startActivity(intent);
                }
                break;
        }

    }

    public Intent chamarMapa(Lugar lugar) {

        String destino[] = lugar.getLocalicao().split(",");
        destinolatitude = Double.parseDouble(destino[0]);
        destinolongitude = Double.parseDouble(destino[1]);

        try {
            startActivity(new Intent (mapa.getMapa(destinolatitude,destinolongitude)));
        } catch (Exception ex){

            Toast.makeText(this ,"Erro",Toast.LENGTH_SHORT).show();
        }
        return mapa.getMapa(destinolatitude,destinolongitude);
    }



}