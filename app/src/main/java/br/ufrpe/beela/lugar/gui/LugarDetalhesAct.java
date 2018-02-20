package br.ufrpe.beela.lugar.gui;

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

import br.ufrpe.beela.avaliacao.gui.AvaliacaoAct;
import br.ufrpe.beela.gui.R;
import br.ufrpe.beela.lugar.dominio.Lugar;
import br.ufrpe.beela.lugar.negocio.LugarService;

/**
 * Created by max on 30/01/18.
 */

public class LugarDetalhesAct extends AppCompatActivity  implements Serializable, View.OnClickListener {

    private ImageButton localizacaoBt;
    private Button classificacao;
    private TextView nome;
    private TextView descricao;
    private TextView nota;
    private ImageView foto;
    private double destinolatitude;
    private double destinolongitude;
    private Lugar recuperado;
    private Bundle bundle;
    LugarService lugarService = new LugarService();

    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_lugar_detalhes);
        setarDetalhes();
    }

    private void setarDetalhes() {
        classificacao = findViewById(R.id.buttonClass);
        classificacao.setOnClickListener(this);

        nome = findViewById(R.id.nomeDoLugar);
        nome.setText(getLugarRecuperado().getNome().toString());

        nota= findViewById(R.id.textView10);
        nota.setText(String.valueOf(getLugarRecuperado().getNotaGeral()));

        descricao = findViewById(R.id.decriçãoLugar);
        descricao.setText(getLugarRecuperado().getDescricao());

        localizacaoBt = findViewById(R.id.imageButtonLocalizacao);
        localizacaoBt.setOnClickListener(this);

        foto = findViewById(R.id.imgViewLugarFoto);
        String recuperar =  getLugarRecuperado().getCaminho();
        int id = getResources().getIdentifier(recuperar,getString(R.string.drawable),getPackageName());
        foto.setImageResource(id);
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.imageButtonLocalizacao:
                chamarMapa(getLugarRecuperado());
                break;

            case R.id.buttonClass:
                Intent intent = new Intent(LugarDetalhesAct.this, AvaliacaoAct.class);
                intent.putExtra(getString(R.string.lugar), getLugarRecuperado());
                startActivity(intent);
                recuperado = lugarService.getLugar(recuperado.getId(),this);

        }
        nota.setText(String.valueOf(recuperado.getNotaGeral()));
    }

    public Intent chamarMapa(Lugar lugar) {
        String destino[] = lugar.getLocalicao().split(",");
        destinolatitude = Double.parseDouble(destino[0]);
        destinolongitude = Double.parseDouble(destino[1]);

        try {
            startActivity(new Intent (lugarService.getMapa(destinolatitude,destinolongitude)));
        } catch (Exception ex){

            Toast.makeText(this , R.string.erro,Toast.LENGTH_SHORT).show();
        }
        return lugarService.getMapa(destinolatitude,destinolongitude);
    }

    public Lugar getLugarRecuperado() {
        bundle = getIntent().getExtras();
        recuperado = (Lugar) bundle.getSerializable(getString(R.string.lugar));
        return recuperado;
    }
}