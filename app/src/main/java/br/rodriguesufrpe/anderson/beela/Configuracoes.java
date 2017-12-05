package br.rodriguesufrpe.anderson.beela;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class Configuracoes extends AppCompatActivity {
//    private TextView TextButton5, TextButton6, TextButton7, Textbutton8;
//    private TextView TextButton11;

    private Button apagarButton7, alterarNomeButton5, alterarSenhaButton6;

    private ArrayList<TextView> textos= new ArrayList<TextView>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracoes);

//      ----------------------------------Alteração da fonte-------------------------------------------

        textos.add((TextView) findViewById(R.id.button5));
        textos.add((TextView) findViewById(R.id.button6));
        textos.add((TextView) findViewById(R.id.button7));
        textos.add((TextView) findViewById(R.id.button8));

        Typeface fonte = Typeface.createFromAsset(getAssets(), "fonts/Chewy.ttf");
        for (TextView txtView: textos) {
            txtView.setTypeface(fonte);
        }
//        TextButton5 = (TextView) findViewById(R.id.button5);
//        TextButton5.setTypeface(fonte);
//
//        TextButton6 = (TextView) findViewById(R.id.button6);
//        TextButton6.setTypeface(fonte);
//
//        TextButton7 = (TextView) findViewById(R.id.button7);
//        TextButton7.setTypeface(fonte);
//
//        Textbutton8 = (TextView) findViewById(R.id.button8);
//        Textbutton8.setTypeface(fonte);

        //---------------------------------Trocar de tela------------------------------------
        //-------------------------------------Alterar Nome----------------------------------------
        alterarNomeButton5 =(Button) findViewById(R.id.button5);
        alterarNomeButton5.setOnClickListener(new View.OnClickListener() {@Override public void onClick (View v){alterarNomeButton5();}});


        //----------------------------------Alterar Senha-------------------------------------

        alterarSenhaButton6 = (Button) findViewById(R.id.button6);
        alterarSenhaButton6.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) {alterarSenhaButton6();}});


        //----------------------------------Apagar Conta-------------------------------------

        apagarButton7 = (Button) findViewById(R.id.button7);
        apagarButton7.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) {apagarButton7();}});
    }

    //----------------------------------Alterar Nome Função -------------------------------------
    private void alterarNomeButton5() {
        startActivity(new Intent(Configuracoes.this, AlterarNome.class));
    }

    //----------------------------------Alterar Senha Função-------------------------------------
    private void alterarSenhaButton6 () {
        startActivity(new Intent(Configuracoes.this, AlterarSenha.class));
    }

//----------------------------------Apagar Conta Função-------------------------------------

    private void apagarButton7() {
        startActivity(new Intent(Configuracoes.this, ApagarConta.class));
    }
}
