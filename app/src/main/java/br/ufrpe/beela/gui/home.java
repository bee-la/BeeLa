package br.ufrpe.beela.gui;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import br.ufrpe.beela.gui.perfil.Perfil;

public class home extends AppCompatActivity {
    private TextView eAi;
    private TextView oQueTuQuer;
    private Button configuracoesImageButton3;
//    private TextView nomeTextView10;
    private Button oQueTuQuerButton4;
    private ImageButton imageButtonPerfil, imageButton2Lugares, imageButton3Configuracoes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
//      TODO: Mudar Fonte
//      ------------------------------Mudar letra------------------------------------------
        Typeface fonte1 = Typeface.createFromAsset(getAssets(), "fonts/Chewy.ttf");

        eAi = (TextView) findViewById(R.id.textView4);
        eAi.setTypeface(fonte1);

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
//        setNomeHome();
    }
//-------------------------------------------Método Trocar de Tela-------------------------------
    private void configuracoesImageButton3() {
        startActivity(new Intent(home.this, Configuracoes.class));
    }

    private void perfilImageButton() {

        startActivity(new Intent(home.this, Perfil.class));
    }
//    private void setNomeHome(){
//        nomeTextView10.setText(Login.usuario.getNome());
//    }
}
