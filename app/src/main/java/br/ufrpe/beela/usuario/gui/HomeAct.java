package br.ufrpe.beela.usuario.gui;

import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import br.ufrpe.beela.gui.R;
import br.ufrpe.beela.lugar.gui.LugaresAct;
import br.ufrpe.beela.perfil.gui.PerfilAct;
import br.ufrpe.beela.usuario.dominio.Usuario;

public class HomeAct extends AppCompatActivity {
    private Usuario usuario = new Usuario();
    private TextView eAi;
    private TextView oQueTuQuer;
    MediaPlayer qualvai;
    MediaPlayer oque;
    private Button configuracoesImageButton3, btoq;
//    private TextView nomeTextView10;
//    private Button oQueTuQuerButton4;
    private ImageButton imageButtonPerfil, imageButton2Lugares, imageButton3Configuracoes ;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

//      TODO: Mudar Fonte
//      ------------------------------Mudar letra------------------------------------------
        Typeface fonte1 = Typeface.createFromAsset(getAssets(), "fonts/Chewy.ttf");

        qualvai =  MediaPlayer.create(this, R.raw.qquall);
        oque = MediaPlayer.create(this, R.raw.oqq);
        eAi = (TextView) findViewById(R.id.textView4);
        eAi.setTypeface(fonte1);
        qualvai.start();
//        nomeTextView10 = (TextView) findViewById(R.id.textView10);
//        nomeTextView10.setTypeface(fonte1);

        oQueTuQuer= (TextView) findViewById(R.id.button4);
        oQueTuQuer.setTypeface(fonte1);

        //      ----------------------Trocar de tela------------------------------------------
        imageButton3Configuracoes = (ImageButton) findViewById(R.id.imageButton3);
        imageButton3Configuracoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                configuracoesImageButton3();}
        });

        imageButtonPerfil = (ImageButton) findViewById(R.id.imageButton);
        imageButtonPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                perfilImageButton();}
        });

        btoq = findViewById(R.id.button4);
        
//        btoq.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                oquetuquer();
//            }
//        });

        imageButton2Lugares = findViewById(R.id.imageButton2);
        imageButton2Lugares.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                lugares();

            }

        });


    }
//-------------------------------------------Método Trocar de Tela-------------------------------
    private void configuracoesImageButton3() {

        startActivity(new Intent(HomeAct.this, ConfiguracoesAct.class));
    }

    private void perfilImageButton() {

        startActivity(new Intent(HomeAct.this, PerfilAct.class));
    }

//    private void oquetuquer(){
//
//        oque.start();
//
//    }

    private void lugares(){

        startActivity(new Intent(HomeAct.this, LugaresAct.class));

    }

}
