package br.rodriguesufrpe.anderson.beela;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class Perfil extends AppCompatActivity {
    private int id;
    private int id_usuario;
    private String nome;
    private String comida;
    private String musica;
    private String esporte;

    private  TextView nomeTextView;
    private ImageButton adicionarPerfilTrocarTela;


    public int getId_Usuario(){return this.id_usuario;}
    public String getNome(){return this.nome;}
    public String getComida(){return this.comida;}
    public String getMusica(){return this.musica;}
    public String getEsporte(){return this.esporte;}

    public void setId(int id){this.id = id;}
    public void setId_usuario(int id_usuario) {this.id_usuario =Login.usuario.getId();}
    public void setNome(String nome){this.nome = nome;}
    public void setComida(String comida){this.comida = comida;}
    public void setMusica(String musica){this.musica = musica;}
    public void setEsporte(String esporte){this.esporte = esporte;}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
//-------------------------------------Trocar tela--------------------------------------------
        Typeface fonte = Typeface.createFromAsset(getAssets(), "fonts/Chewy.ttf");
        nomeTextView =(TextView)findViewById(R.id.textViewPerfil1);
        nomeTextView.setTypeface(fonte);
        adicionarPerfilTrocarTela = (ImageButton) findViewById(R.id.imageButton4);
        adicionarPerfilTrocarTela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adicionarPerfilTrocarTela();}

        });
    }
    private void adicionarPerfilTrocarTela() {
        startActivity(new Intent(Perfil.this, PerguntasComidas.class));
        Login.usuario.perfil.setNome("TESTEPORRA");
        setarNome();
    }
    private ArrayList chamarPerfil(){
        ArrayList<Perfil> listaPerfil = new ArrayList<Perfil>();
        BDcomandos bd = new BDcomandos(this,"R");
        return listaPerfil = bd.getPerfil();
    }
    private void setarNome(){
        nomeTextView.setText(Login.usuario.perfil.getNome());
    }
}
