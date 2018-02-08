package br.ufrpe.beela.lugar.gui;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
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

public class LugarDetalhesAct extends AppCompatActivity  implements Serializable, View.OnClickListener {

    private ImageButton localizacaoBt, siteBt;
    private Button classificacao;
    private TextView nome,descricao;
    private ImageView foto;
    private double destinolatitude;
    private double destinolongitude;
    private Lugar recuperado;
    private Bundle bundle;
    LugarService mapa = new LugarService();

    @Override
    protected void onCreate(Bundle saveInstanceState){

        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_lugar_detalhes);
        bundle = getIntent().getExtras();
        recuperado = (Lugar) bundle.getSerializable(getString(R.string.lugar));
        setarDetalhes();
    }

    private void setarDetalhes() {
        classificacao = (Button)findViewById(R.id.buttonClass);
        classificacao.setOnClickListener(this);

        nome = (TextView)findViewById(R.id.nomeDoLugar);
        nome.setText(recuperado.getNome().toString());

        descricao = findViewById(R.id.decriçãoLugar);
        descricao.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                "Quisque maximus feugiat porttitor. Sed scelerisque erat eu diam sollicitudin " +
                "tristique id quis tellus. Aliquam eget suscipit eros.");

        localizacaoBt = (ImageButton) findViewById(R.id.imageButtonLocalizacao);
        localizacaoBt.setOnClickListener(this);

//        siteBt = (ImageButton) findViewById(R.id.imageButtonSite);
//        siteBt.setOnClickListener(this);

        foto = (ImageView)findViewById(R.id.imgViewLugarFoto);
        String recuperar =  recuperado.getCaminho();
        int id = getResources().getIdentifier(recuperar,getString(R.string.drawable),getPackageName());
        foto.setImageResource(id);
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.imageButtonLocalizacao:
                chamarMapa(recuperado);
                break;
//            case R.id.imageButtonSite:
//                Intent intent = new Intent(Intent.ACTION_SEARCH);
//                intent.putExtra(SearchManager.QUERY , recuperado.getNome().toString());
//                if (intent.resolveActivity(getPackageManager()) != null){
//                    startActivity(intent);
//                }
//                break;
            case R.id.buttonClass:
                startActivity(new Intent(LugarDetalhesAct.this, AvaliacaoAct.class));
        }
    }

    public Intent chamarMapa(Lugar lugar) {
        String destino[] = lugar.getLocalicao().split(",");
        destinolatitude = Double.parseDouble(destino[0]);
        destinolongitude = Double.parseDouble(destino[1]);

        try {
            startActivity(new Intent (mapa.getMapa(destinolatitude,destinolongitude)));
        } catch (Exception ex){

            Toast.makeText(this , R.string.erro,Toast.LENGTH_SHORT).show();
        }
        return mapa.getMapa(destinolatitude,destinolongitude);
    }



}