package br.ufrpe.beela.perfil.gui;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import br.ufrpe.beela.perfil.dominio.PerfilUsuario;
import br.ufrpe.beela.usuario.dominio.Usuario;
import br.ufrpe.beela.usuario.gui.LoginAct;
import br.ufrpe.beela.gui.R;

public class PerfilAct extends AppCompatActivity {
    private PerfilUsuario usuario = LoginAct.getUsuario().getPerfil();
    private  TextView nomeTextView;
    private ImageButton adicionarPerfilTrocarTela;


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
        setarNome();
        startActivity(new Intent(PerfilAct.this, PerguntasComidasAct.class));
    }

    private void setarNome(){
        if (usuario.getNome() == ""){
        nomeTextView.setText(usuario.getNome());}
        else{
        nomeTextView.setText(usuario.getNome());}

    }
}
