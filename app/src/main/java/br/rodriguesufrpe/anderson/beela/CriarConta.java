package br.rodriguesufrpe.anderson.beela;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CriarConta extends AppCompatActivity {
    private TextView nome;
    private TextView celular;
    private TextView email;
    private TextView senha;
    private TextView repetirSenha;
    private Button criarButton3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_conta);

        // --------------------------Alteração da letra-------------------------------------------

        nome = (TextView) findViewById(R.id.editText4);
        Typeface fonte1 = Typeface.createFromAsset(getAssets(), "fonts/Chewy.ttf");
        nome.setTypeface(fonte1);

        celular = (TextView) findViewById(R.id.editText5);
        Typeface fonte2 = Typeface.createFromAsset(getAssets(), "fonts/Chewy.ttf");
        celular.setTypeface(fonte2);

        email = (TextView) findViewById(R.id.editText6);
        Typeface fonte3 = Typeface.createFromAsset(getAssets(), "fonts/Chewy.ttf");
        email.setTypeface(fonte3);

        senha = (TextView) findViewById(R.id.editText10);
        Typeface fonte4 = Typeface.createFromAsset(getAssets(), "fonts/Chewy.ttf");
        senha.setTypeface(fonte4);

        repetirSenha = (TextView) findViewById(R.id.editText11);
        Typeface fonte5 = Typeface.createFromAsset(getAssets(), "fonts/Chewy.ttf");
        repetirSenha.setTypeface(fonte5);

        //      ----------------------Trocar de tela------------------------------------------
        criarButton3 = (Button) findViewById(R.id.button3);
        criarButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                criarButton3();}
        });
    }

    private void criarButton3() {
        startActivity(new Intent(CriarConta.this, home.class));
    }

}
