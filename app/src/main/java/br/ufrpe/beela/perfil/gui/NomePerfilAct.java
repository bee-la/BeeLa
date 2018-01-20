package br.ufrpe.beela.perfil.gui;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.ufrpe.beela.database.negocio.BancoDeDados;
import br.ufrpe.beela.lugar.dao.LugarDAO;
import br.ufrpe.beela.lugar.dominio.Lugar;
import br.ufrpe.beela.perfil.dominio.PerfilComida;
import br.ufrpe.beela.perfil.dominio.PerfilEsporte;
import br.ufrpe.beela.perfil.dominio.PerfilMusica;
import br.ufrpe.beela.perfil.dominio.PerfilUsuario;
import br.ufrpe.beela.perfil.negocio.PerfilService;
import br.ufrpe.beela.usuario.dominio.Usuario;
import br.ufrpe.beela.usuario.gui.LoginAct;
import br.ufrpe.beela.perfil.dao.PerfilDAO;
import br.ufrpe.beela.gui.R;

public class NomePerfilAct extends AppCompatActivity {
    private PerfilUsuario perfilUsuario = LoginAct.getPessoa().getPerfil();
    private PerfilService perfilService = new PerfilService();
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
                verificarNomePerfil();
            }
        });
    }

    public void verificarNomePerfil(){
        nomePerfil=campoNomePerfil.getText().toString().trim();
        if(perfilService.verificarNomeIgual(nomePerfil)){
            if (perfilService.verificarNomeVazio(nomePerfil)) {
                perfilUsuario.setNome(nomePerfil);
                LoginAct.getPessoa().setPerfil(perfilUsuario);
                salvarBD();
                irTelaPerfil();
            }
            else{
                erro =Toast.makeText(getApplicationContext(),R.string.campoVazio, Toast.LENGTH_SHORT);
                erro.show();
            }
        }
        else{
            erro =Toast.makeText(getApplicationContext(), R.string.perfilExiste, Toast.LENGTH_SHORT);
            erro.show();
        }
    }

    public void salvarBD() {
        perfilService.adcPerfil(perfilUsuario, this);
        perfilService.adcPerfilFavorito(LoginAct.getPessoa().getId(),nomePerfil,this);
    }

    public void irTelaPerfil(){
        Intent intent = new Intent();
        intent.putExtra("nomePerfil",nomePerfil);
    //    startActivity(new Intent(this, PerfilAct.class));
        setResult(1,intent);
        finish();
    }
}
