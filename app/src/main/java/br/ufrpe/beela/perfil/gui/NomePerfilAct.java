package br.ufrpe.beela.perfil.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import br.ufrpe.beela.perfil.dominio.PerfilUsuario;
import br.ufrpe.beela.usuario.gui.LoginAct;
import br.ufrpe.beela.perfil.dao.PerfilDAO;
import br.ufrpe.beela.gui.R;

public class NomePerfilAct extends AppCompatActivity {
    private PerfilUsuario usuario = LoginAct.getUsuario().getPerfil();

    private EditText nomePerfilEditText12;
    private TextView nomeTextViewPerfil1;
    private Button nomearButton22;

    private String nomePerfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nome_perfil);

        nomeTextViewPerfil1=(TextView)findViewById(R.id.textViewPerfil1);
        nomePerfilEditText12=(EditText)findViewById(R.id.editText12);
        nomearButton22=(Button)findViewById(R.id.button22);

        nomePerfil=nomePerfilEditText12.getText().toString().trim();

        nomearButton22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chamarSetarNomePerfil(nomePerfil); }});
    }
    
//TODO      NullPointExcept ao chamar essa função
    public void chamarSetarNomePerfil(String nomePerfil){
        usuario.setNome(nomePerfil);
        PerfilDAO bd = new PerfilDAO(this,"W");
        bd.inserirPerfil(usuario);
        startActivity(new Intent(NomePerfilAct.this, PerfilAct.class));
    }


}
