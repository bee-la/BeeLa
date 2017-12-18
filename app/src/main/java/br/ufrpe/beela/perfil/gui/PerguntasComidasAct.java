package br.ufrpe.beela.perfil.gui;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.ufrpe.beela.perfil.dominio.PerfilUsuario;
import br.ufrpe.beela.usuario.gui.LoginAct;
import br.ufrpe.beela.gui.R;

public class PerguntasComidasAct extends AppCompatActivity {
    private PerfilUsuario usuario = LoginAct.getUsuario().getPerfil();

    private Button RespostaButton13;
    private Button RespostaButton15;
    private Button RespostaButton16;
    private Button RespostaButton17;

    private PerguntasRespostas perguntasTela=new PerguntasRespostas();

    private List<String> comidaEscolhida=new ArrayList<String>();
    private String comidaSelecionada;
    private Toast mensagem;
    private int contador;
    private TextView ComidaPreferidatextView7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perguntas_comidas);
//---------------------------Alterar Fonte---------------------------------------
        Typeface fonte = Typeface.createFromAsset(getAssets(), "fonts/Chewy.ttf");
        ComidaPreferidatextView7 = (TextView) findViewById(R.id.textView7);
        ComidaPreferidatextView7.setTypeface(fonte);
//------------------------------------------------------------------------------------


        RespostaButton13=(Button)findViewById(R.id.button13);
        RespostaButton15=(Button)findViewById(R.id.button15);
        RespostaButton16=(Button)findViewById(R.id.button16);
        RespostaButton17=(Button)findViewById(R.id.button17);

        RespostaButton13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {atualizarRespostasMassa();}
        });
        RespostaButton15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {atualizarRespostasOriental();}
        });
        RespostaButton16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {atualizarRespostasCarne();}
        });
        RespostaButton17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                comidaSelecionada =RespostaButton17.getText().toString();
                mensagem=Toast.makeText(getApplicationContext(), "Sua Escolha: "+comidaSelecionada, Toast.LENGTH_SHORT);
                mensagem.show();

                alterarTelaMusica();
            }
        });
    }

    private void atualizarRespostasMassa(){
        if(contador==1){
            comidaSelecionada = RespostaButton13.getText().toString();
            mensagem=Toast.makeText(getApplicationContext(), "Sua Escolha: "+comidaSelecionada, Toast.LENGTH_SHORT);
            mensagem.show();
            alterarTelaMusica();

        }
        else {
            RespostaButton13.setText(perguntasTela.getMassas()[0]);
            RespostaButton15.setText(perguntasTela.getMassas()[1]);
            RespostaButton16.setText(perguntasTela.getMassas()[2]);
            RespostaButton17.setText(perguntasTela.getMassas()[3]);
            contador += 1;
        }
    }

    private void atualizarRespostasOriental(){
        if(contador==1){
            comidaSelecionada = RespostaButton15.getText().toString();
            mensagem=Toast.makeText(getApplicationContext(), "Sua Escolha: "+comidaSelecionada, Toast.LENGTH_SHORT);
            mensagem.show();
            alterarTelaMusica();

        }
        else {
            RespostaButton13.setText(perguntasTela.getOriental()[0]);
            RespostaButton15.setText(perguntasTela.getOriental()[1]);
            RespostaButton16.setText(perguntasTela.getOriental()[2]);
            RespostaButton17.setText(perguntasTela.getOriental()[3]);
            contador+= 1;
        }
    }

    private void atualizarRespostasCarne(){
        if(contador==1){
            comidaSelecionada="";
            comidaSelecionada+=RespostaButton16.getText();
            mensagem=Toast.makeText(getApplicationContext(), "Sua Escolha: "+comidaSelecionada, Toast.LENGTH_SHORT);
            mensagem.show();
            alterarTelaMusica();

        }
        else {
            RespostaButton13.setText(perguntasTela.getCarnes()[0]);
            RespostaButton15.setText(perguntasTela.getCarnes()[1]);
            RespostaButton16.setText(perguntasTela.getCarnes()[2]);
            RespostaButton17.setText(perguntasTela.getCarnes()[3]);
            contador+= 1;
        }
    }

    private void alterarTelaMusica(){
//        usuario.setComida(comidaSelecionada);
        startActivity(new Intent(PerguntasComidasAct.this, PerguntasMusicaAct.class));
    }
}
