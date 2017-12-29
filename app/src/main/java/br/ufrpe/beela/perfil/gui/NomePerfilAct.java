package br.ufrpe.beela.perfil.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import br.ufrpe.beela.perfil.dominio.PerfilComida;
import br.ufrpe.beela.perfil.dominio.PerfilMusica;
import br.ufrpe.beela.perfil.dominio.PerfilUsuario;
import br.ufrpe.beela.usuario.dominio.Usuario;
import br.ufrpe.beela.usuario.gui.LoginAct;
import br.ufrpe.beela.perfil.dao.PerfilDAO;
import br.ufrpe.beela.gui.R;

public class NomePerfilAct extends AppCompatActivity {
    private Usuario usuario = LoginAct.getUsuario();
    private PerfilUsuario perfilUsuario = LoginAct.getPessoa().getPerfil();
    private EditText nomePerfilEditText12;
    private TextView nomeTextViewPerfil1;
    private Button nomearButton22;

    private String nomePerfil="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_nome_perfil);

        nomeTextViewPerfil1=(TextView)findViewById(R.id.textViewPerfil1);
        nomePerfilEditText12=(EditText)findViewById(R.id.editText12);
        nomearButton22=(Button)findViewById(R.id.button22);

        nomePerfil=nomePerfilEditText12.getText().toString().trim();
        nomearButton22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {setNomePerfil();}});}
    public void setNomePerfil(){
        nomePerfil=nomePerfilEditText12.getText().toString().trim();
        PerfilDAO bd = new PerfilDAO(this,"R");
        if(bd.buscarPerfil(usuario,nomePerfil)){
            perfilUsuario.setNome(nomePerfil);
            botarnominho();
            irembora();
            }
        else{
            Toast Erro;
            Erro =Toast.makeText(getApplicationContext(), "Nome do Perfil igual", Toast.LENGTH_SHORT);
            Erro.show(); }}
    public void botarnominho(){

        for (PerfilComida comida : perfilUsuario.getComida()){
            PerfilDAO bd = new PerfilDAO(this,"W");
            comida.setNome_perfil(perfilUsuario.getNome());
            bd.inserirPerfilComida(comida);}

        for (PerfilMusica musica : perfilUsuario.getMusica()){
            PerfilDAO bd = new PerfilDAO(this,"W");
            musica.setNome_perfil(perfilUsuario.getNome());
            bd.inserirPerfilMusica(musica);}
        PerfilDAO bd = new PerfilDAO(this,"W");
        bd.inserirPerfil(perfilUsuario);
    }
    public void irembora(){
        Bundle parametros = new Bundle();
        parametros.putString("chave",nomePerfilEditText12.getText().toString());
        Intent it = new Intent(this, PerfilAct.class);
        it.putExtras(parametros);
        startActivity(it);
        finish();
    }

}
