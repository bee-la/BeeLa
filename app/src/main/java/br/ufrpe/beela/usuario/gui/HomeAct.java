package br.ufrpe.beela.usuario.gui;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

import br.ufrpe.beela.database.negocio.BancoDeDados;
import br.ufrpe.beela.gui.R;
import br.ufrpe.beela.lugar.dao.LugarDAO;
import br.ufrpe.beela.lugar.dominio.Lugar;
import br.ufrpe.beela.lugar.gui.LugarAcompanhadoAct;
import br.ufrpe.beela.lugar.gui.LugarAct;
import br.ufrpe.beela.lugar.negocio.GoogleMaps;
import br.ufrpe.beela.perfil.gui.PerfilAct;

public class HomeAct extends AppCompatActivity {
    private TextView eAi;
    private TextView oQueTuQuer;
    private ImageButton botaoPerfil, botaoLugares, botaoConfiguracoes;
    private BancoDeDados bancoDeDados = new BancoDeDados();

    private Button oqTuQuer;
    private LugarDAO lugarDAO = new LugarDAO();
    private GoogleMaps mapa=new GoogleMaps();
    private ArrayList<Lugar> lugaresPreferidos = new ArrayList<Lugar>();

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
        irOqTuQuer();

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

    private void irOqTuQuer(){
        oqTuQuer=(Button)findViewById(R.id.button4);
        oqTuQuer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                criarLugares();
                Collections.shuffle(lugaresPreferidos);
                Lugar lugarAleatorio=lugaresPreferidos.get(0);
                mapa.chamarMapa(lugarAleatorio);
            }
        });
    }


//TODO    Função teste porque não está retornando os lugares do banco
    public void criarLugares() {
        Lugar ru = new Lugar();
        ru.setNome("RU - UFRPE");
        ru.setDescriacao("Self-Service");
        ru.setLocalicao("-8.014121,-34.951131");
        lugaresPreferidos.add(ru);

        Lugar igreja = new Lugar();
        igreja.setNome("Igreja Do Carmmo");
        igreja.setDescriacao("Igreja");
        igreja.setLocalicao("-8.0168458,-34.849372");
        lugaresPreferidos.add(igreja);

        Lugar rock = new Lugar();
        rock.setNome("RockRibs");
        rock.setDescriacao("Restaurante");
        rock.setLocalicao("-8.0640944,-34.8714444");
        lugaresPreferidos.add(rock);
    }

}
