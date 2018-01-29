package br.ufrpe.beela.usuario.gui;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import br.ufrpe.beela.database.negocio.BancoDeDados;
import br.ufrpe.beela.gui.R;
import br.ufrpe.beela.lugar.gui.LugarAcompanhadoAct;
import br.ufrpe.beela.perfil.gui.PerfilAct;

public class HomeAct extends AppCompatActivity {
    private TextView eAi;
    private TextView oQueTuQuer;
    private ImageButton botaoPerfil, botaoLugares, botaoConfiguracoes;
    private BancoDeDados bancoDeDados = new BancoDeDados();
//    MediaPlayer qualvai;
//    MediaPlayer oque;

//    private Button btoq;

//    private Button oQueTuQuerButton4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        bancoDeDados.gerarLugares(this);
        alterarFonte();
        irPerfil();
        irConfiguracoes();
        irLugares();

//        btoq = findViewById(R.id.button4);
    }

    private void alterarFonte() {
        Typeface fonte = Typeface.createFromAsset(getAssets(), "fonts/Chewy.ttf");
        eAi = (TextView) findViewById(R.id.textView4);
        eAi.setTypeface(fonte);

        oQueTuQuer = (TextView) findViewById(R.id.button4);
        oQueTuQuer.setTypeface(fonte);
    }

    private void irPerfil() {
        botaoPerfil = (ImageButton) findViewById(R.id.imageButton);
        botaoPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeAct.this, PerfilAct.class));
            }
        });
    }

    private void irLugares() {
        botaoLugares = findViewById(R.id.imageButton2);
        botaoLugares.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeAct.this, LugarAcompanhadoAct.class));
            }
        });
    }

    private void irConfiguracoes() {
        botaoConfiguracoes = (ImageButton) findViewById(R.id.imageButton3);
        botaoConfiguracoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeAct.this, ConfiguracoesAct.class));
//                configuracoesImageButton3();
            }
        });
    }

}
