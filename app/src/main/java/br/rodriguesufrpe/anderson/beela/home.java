package br.rodriguesufrpe.anderson.beela;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class home extends AppCompatActivity {
    private TextView eAi;
    private TextView oQueTuQuer;
    private Button configuracoesImageButton3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

//      ------------------------------Mudar letra------------------------------------------
        eAi = (TextView) findViewById(R.id.textView4);
        Typeface fonte1 = Typeface.createFromAsset(getAssets(), "fonts/Chewy.ttf");
        eAi.setTypeface(fonte1);

        oQueTuQuer= (TextView) findViewById(R.id.button4);
        Typeface fonte2 = Typeface.createFromAsset(getAssets(), "fonts/Chewy.ttf");
        oQueTuQuer.setTypeface(fonte2);

        //      ----------------------Trocar de tela------------------------------------------
        configuracoesImageButton3 = (Button) findViewById(R.id.button4);
        configuracoesImageButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                configuracoesImageButton3();}
        });
    }

    private void configuracoesImageButton3() {
        startActivity(new Intent(home.this, Configuracoes.class));
    }
}
