package br.rodriguesufrpe.anderson.beela;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Configuracoes extends AppCompatActivity {
    private TextView TextButton5;
    private TextView TextButton6;
    private TextView TextButton7;
    private TextView Textbutton8;
    private TextView TextButton11;

    private Button apagarButton7;
    private Button alterarNomeButton5;
    private Button alterarSenhaButton6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracoes);

//      ----------------------------------Mudar letra-------------------------------------------
        TextButton5 = (TextView) findViewById(R.id.button5);
        Typeface fonte1 = Typeface.createFromAsset(getAssets(), "fonts/Chewy.ttf");
        TextButton5.setTypeface(fonte1);

        TextButton6 = (TextView) findViewById(R.id.button6);
        Typeface fonte2 = Typeface.createFromAsset(getAssets(), "fonts/Chewy.ttf");
        TextButton6.setTypeface(fonte2);

        TextButton7 = (TextView) findViewById(R.id.button7);
        Typeface fonte3 = Typeface.createFromAsset(getAssets(), "fonts/Chewy.ttf");
        TextButton7.setTypeface(fonte3);

        Textbutton8 = (TextView) findViewById(R.id.button8);
        Typeface fonte4 = Typeface.createFromAsset(getAssets(), "fonts/Chewy.ttf");
        Textbutton8.setTypeface(fonte4);

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

//----------------------------------Alterar Nome Método-------------------------------------
    private void alterarNomeButton5() {
        startActivity(new Intent(Configuracoes.this, AlterarNome.class));
    }

    //----------------------------------Alterar Senha Método-------------------------------------
    private void alterarSenhaButton6 () {
        startActivity(new Intent(Configuracoes.this, AlterarSenha.class));
    }

//----------------------------------Apagar Conta Método-------------------------------------

    private void apagarButton7() {
        startActivity(new Intent(Configuracoes.this, ApagarConta.class));
    }
}
