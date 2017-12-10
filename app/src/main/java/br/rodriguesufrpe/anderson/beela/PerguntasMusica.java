package br.rodriguesufrpe.anderson.beela;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class PerguntasMusica extends AppCompatActivity {
    private TextView tipoMusicaTextView9;
    private Toast mensagem;
    private String musicaSelecionada;
    private Button forroButton18, sambaButton19, sertanejoButton20, rockButton21;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perguntas_musica);

        Typeface fonte = Typeface.createFromAsset(getAssets(), "fonts/Chewy.ttf");

        tipoMusicaTextView9 = (TextView) findViewById(R.id.textView9);
        tipoMusicaTextView9.setTypeface(fonte);


        forroButton18=(Button)findViewById(R.id.button18);
        sambaButton19=(Button)findViewById(R.id.button19);
        sertanejoButton20=(Button)findViewById(R.id.button20);
        rockButton21=(Button)findViewById(R.id.button21);

        forroButton18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                musicaSelecionada="";
                musicaSelecionada+=forroButton18.getText();
                mensagem=Toast.makeText(getApplicationContext(), "Sua Escolha: "+musicaSelecionada, Toast.LENGTH_SHORT);
                mensagem.show();
                alterarTelaPerfil();
            }
        });

        sambaButton19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                musicaSelecionada="";
                musicaSelecionada+=sambaButton19.getText();
                mensagem=Toast.makeText(getApplicationContext(), "Sua Escolha: "+musicaSelecionada, Toast.LENGTH_SHORT);
                mensagem.show();
                alterarTelaPerfil();
            }
        });

        sertanejoButton20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                musicaSelecionada="";
                musicaSelecionada+=sertanejoButton20.getText();
                mensagem=Toast.makeText(getApplicationContext(), "Sua Escolha: "+musicaSelecionada, Toast.LENGTH_SHORT);
                mensagem.show();
                alterarTelaPerfil();
            }
        });

        rockButton21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                musicaSelecionada="";
                musicaSelecionada+=rockButton21.getText();
                mensagem=Toast.makeText(getApplicationContext(), "Sua Escolha: "+musicaSelecionada, Toast.LENGTH_SHORT);
                mensagem.show();
                alterarTelaPerfil();
            }
        });
    }

    public void alterarTelaPerfil(){
//TODO      Na tela de Perfil falta aparecer o perfil que foi adicionado.
        startActivity(new Intent(PerguntasMusica.this, Perfil.class));

    }
}
