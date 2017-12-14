package br.ufrpe.beela.perfil.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


<<<<<<< HEAD:app/src/main/java/br/ufrpe/beela/perfil/gui/NomePerfilAct.java
import br.ufrpe.beela.perfil.dao.BDcomandosPerfil;
import br.ufrpe.beela.usuario.gui.LoginAct;
=======
import br.ufrpe.beela.perfil.dao.PerfilDAO;
import br.ufrpe.beela.usuario.gui.GUILogin;
>>>>>>> desenvolvedor2:app/src/main/java/br/ufrpe/beela/perfil/gui/NomePerfil.java
import br.ufrpe.beela.gui.R;

public class NomePerfilAct extends AppCompatActivity {

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
<<<<<<< HEAD:app/src/main/java/br/ufrpe/beela/perfil/gui/NomePerfilAct.java
        LoginAct.usuario.getPerfil().setNome(nomePerfil);
        BDcomandosPerfil bd = new BDcomandosPerfil(this,"W");
        bd.inserirPerfil(LoginAct.usuario.getPerfil());
        startActivity(new Intent(NomePerfilAct.this, PerfilAct.class));
=======
        GUILogin.usuario.getPerfil().setNome(nomePerfil);
        PerfilDAO bd = new PerfilDAO(this,"W");
        bd.inserirPerfil(GUILogin.usuario.getPerfil());
        startActivity(new Intent(NomePerfil.this, Perfil.class));
>>>>>>> desenvolvedor2:app/src/main/java/br/ufrpe/beela/perfil/gui/NomePerfil.java
    }


}
