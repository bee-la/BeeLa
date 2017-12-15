package br.ufrpe.beela.perfil.gui;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import br.ufrpe.beela.usuario.gui.LoginAct;
import br.ufrpe.beela.gui.R;

public class PerguntasMusicaAct extends AppCompatActivity {
    private TextView fonteTextView9;
    private Toast mensagem;
    private String musicaSelecionada;
    private Button forroButton18;
    private Button sambaButton19;
    private Button sertanejoButton20;
    private Button rockButton21;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perguntas_musica);

        Typeface fonte = Typeface.createFromAsset(getAssets(), "fonts/Chewy.ttf");

        fonteTextView9 = (TextView) findViewById(R.id.textView9);
        fonteTextView9.setTypeface(fonte);


        forroButton18=(Button)findViewById(R.id.button18);
        sambaButton19=(Button)findViewById(R.id.button19);
        sertanejoButton20=(Button)findViewById(R.id.button20);
        rockButton21=(Button)findViewById(R.id.button21);

        forroButton18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                musicaSelecionada=forroButton18.getText().toString();
                mensagem=Toast.makeText(getApplicationContext(), "Sua Escolha: "+musicaSelecionada, Toast.LENGTH_SHORT);
                mensagem.show();
               // LoginAct.usuario.perfil.setMusica(musicaSelecionada);
                alterarTelaPerfil();
            }
        });

        sambaButton19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                musicaSelecionada=sambaButton19.getText().toString();
                mensagem=Toast.makeText(getApplicationContext(), "Sua Escolha: "+musicaSelecionada, Toast.LENGTH_SHORT);
                mensagem.show();
             //   LoginAct.usuario.perfil.setMusica(musicaSelecionada);
                alterarTelaPerfil();
            }
        });

        sertanejoButton20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                musicaSelecionada=sertanejoButton20.getText().toString();
                mensagem=Toast.makeText(getApplicationContext(), "Sua Escolha: "+musicaSelecionada, Toast.LENGTH_SHORT);
                mensagem.show();
           //     LoginAct.usuario.perfil.setMusica(musicaSelecionada);
                alterarTelaPerfil();
            }
        });

        rockButton21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                musicaSelecionada=rockButton21.getText().toString();
                mensagem=Toast.makeText(getApplicationContext(), "Sua Escolha: "+musicaSelecionada, Toast.LENGTH_SHORT);
                mensagem.show();
// TO SETANDO DIRETO OS ATRIBUTOS
               // LoginAct.usuario.perfil.setMusica(musicaSelecionada);
                alterarTelaPerfil();
            }
        });
//        LoginAct.usuario.perfil.setNome(musicaSelecionada);

    }

    public void alterarTelaPerfil(){
//TODO      Na tela de PerfilAct falta aparecer o perfil que foi adicionado.
        LoginAct.usuario.getPerfil().setMusica(musicaSelecionada);
        startActivity(new Intent(PerguntasMusicaAct.this, NomePerfilAct.class));

    }

}
