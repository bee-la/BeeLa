package br.ufrpe.beela.usuario.gui;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import br.ufrpe.beela.database.negocio.BancoDeDados;
import br.ufrpe.beela.gui.R;
import br.ufrpe.beela.lugar.gui.LugarAct;
import br.ufrpe.beela.lugar.gui.teste;
import br.ufrpe.beela.perfil.gui.PerfilAct;
import br.ufrpe.beela.usuario.dominio.Usuario;

public class HomeAct extends AppCompatActivity implements View.OnClickListener {
    private TextView eAi;
    private TextView oQueTuQuer;
    private ImageButton botaoPerfil, botaoLugares, botaoConfiguracoes;
    private BancoDeDados bancoDeDados = new BancoDeDados();

    AlertDialog cep;

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
        criarBotoesTela();
        pegarCep();
//        btoq = findViewById(R.id.button4);
    }

    private void criarBotoesTela() {
        botaoPerfil = (ImageButton) findViewById(R.id.imageButton);
        botaoPerfil.setOnClickListener(this);
        botaoConfiguracoes = (ImageButton) findViewById(R.id.imageButton3);
        botaoConfiguracoes.setOnClickListener(this);
        botaoLugares = findViewById(R.id.imageButton2);
        botaoLugares.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.imageButton:
                startActivity(new Intent(HomeAct.this, PerfilAct.class));
                break;

            case R.id.imageButton3:
                startActivity(new Intent(HomeAct.this, ConfiguracoesAct.class));

            case R.id.imageButton2:
                startActivity(new Intent(HomeAct.this, teste.class));




        }


    }


    private void pegarCep(){


    LayoutInflater ly = getLayoutInflater();
    View view = ly.inflate(R.layout.cep,null);
    view.findViewById(R.id.btCep).setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //exibe um Toast informativo.
            Toast.makeText(HomeAct.this, "alerta.dismiss()", Toast.LENGTH_SHORT).show();
            //desfaz o alerta.
            cep.dismiss();



        }
    });


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Informe");
        builder.setView(view);
        cep = builder.create();
        cep.show();



    }

    private void alterarFonte(){
        Typeface fonte = Typeface.createFromAsset(getAssets(), "fonts/Chewy.ttf");
        eAi = (TextView) findViewById(R.id.textView4);
        eAi.setTypeface(fonte);

        oQueTuQuer= (TextView) findViewById(R.id.button4);
        oQueTuQuer.setTypeface(fonte);
    }
// - Remover - substituido switch case
//    private void irPerfil() {
//        botaoPerfil = (ImageButton) findViewById(R.id.imageButton);
//        botaoPerfil.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(HomeAct.this, PerfilAct.class));
//            }
//        });
//    }
//
//    private void irLugares(){
//        botaoLugares = findViewById(R.id.imageButton2);
//        botaoLugares.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v){
//                startActivity(new Intent(HomeAct.this, teste.class));
//            }
//        });
//    }
//
//    private void irConfiguracoes() {
//        botaoConfiguracoes = (ImageButton) findViewById(R.id.imageButton3);
//        botaoConfiguracoes.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(HomeAct.this, ConfiguracoesAct.class));
////                configuracoesImageButton3();
//            }
//        });
//    }


}
