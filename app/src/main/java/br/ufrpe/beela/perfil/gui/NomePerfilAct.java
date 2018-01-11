package br.ufrpe.beela.perfil.gui;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import br.ufrpe.beela.perfil.dominio.PerfilComida;
import br.ufrpe.beela.perfil.dominio.PerfilEsporte;
import br.ufrpe.beela.perfil.dominio.PerfilMusica;
import br.ufrpe.beela.perfil.dominio.PerfilUsuario;
import br.ufrpe.beela.usuario.dominio.Usuario;
import br.ufrpe.beela.usuario.gui.LoginAct;
import br.ufrpe.beela.perfil.dao.PerfilDAO;
import br.ufrpe.beela.gui.R;

public class NomePerfilAct extends AppCompatActivity {
    private Usuario usuario = LoginAct.getUsuario();
    private PerfilUsuario perfilUsuario = LoginAct.getPessoa().getPerfil();
    private EditText campoNomePerfil;
    private Button botaoNomear;
    private Toast erro;
    private String nomePerfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_nome_perfil);

        campoNomePerfil=(EditText)findViewById(R.id.editText12);
        alterarFonte();
        clicarBotaoNomear();

    }

    private void alterarFonte(){
        Typeface fonte = Typeface.createFromAsset(getAssets(), "fonts/Chewy.ttf");
        campoNomePerfil.setTypeface(fonte);
    }

    private void clicarBotaoNomear(){
        botaoNomear=(Button)findViewById(R.id.button22);
        botaoNomear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setNomePerfil();
            }
        });
    }

    public void setNomePerfil(){
        nomePerfil=campoNomePerfil.getText().toString().trim();
        if(verificarNomeIgual(nomePerfil)){//TODO    Falta verificar campo vazio.
            perfilUsuario.setNome(nomePerfil);
            nomearPerfil();
            irTelaPerfil();
        }
        else{
            erro =Toast.makeText(getApplicationContext(), R.string.perfilExiste, Toast.LENGTH_SHORT);
            erro.show();
        }
    }

    public void nomearPerfil(){
        adcComida();
        adcMusica();
        adcEsporte();
        PerfilDAO bd = new PerfilDAO();
        bd.getEscrever(this);
        bd.inserirPerfil(perfilUsuario);
    }

    public void irTelaPerfil(){
        startActivity(new Intent(this, PerfilAct.class));
        finish();
    }
    public boolean verificarNomeIgual(String NomePerfil){
        boolean saida = true;
        for (PerfilUsuario perfilUsuario:PerfilAct.getLista())
            if (perfilUsuario.getNome().equals(NomePerfil)) {
                saida = false;
                break;
        }
        return saida;
    }

    private void adcComida(){
        for (PerfilComida comida : perfilUsuario.getComida()){
            PerfilDAO bd = new PerfilDAO();
            bd.getEscrever(this);
            comida.setNome_perfil(perfilUsuario.getNome());
            bd.inserirPerfilComida(comida);
        }
    }

    private void adcMusica(){
        for (PerfilMusica musica : perfilUsuario.getMusica()){
            PerfilDAO bd = new PerfilDAO();
            bd.getEscrever(this);
            musica.setNome_perfil(perfilUsuario.getNome());
            bd.inserirPerfilMusica(musica);
        }
    }

    private void adcEsporte(){
        for (PerfilEsporte esporte : perfilUsuario.getEsporte()){
            PerfilDAO bd = new PerfilDAO();
            bd.getEscrever(this);
            esporte.setNome_perfil(perfilUsuario.getNome());
            bd.inserirPerfilEsporte(esporte);}
    }
}
