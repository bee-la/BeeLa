package br.ufrpe.beela.perfil.gui;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import br.ufrpe.beela.perfil.dominio.PerfilUsuario;
import br.ufrpe.beela.perfil.negocio.PerfilService;
import br.ufrpe.beela.usuario.gui.LoginAct;
import br.ufrpe.beela.gui.R;

public class NomePerfilAct extends AppCompatActivity {
    private PerfilUsuario perfilUsuario = LoginAct.getPessoa().getPerfilAtual();
    private PerfilService perfilService = new PerfilService();
    private EditText campoNomePerfil;
    private Button botaoNomear;
    private Toast erro;
    private String nomePerfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nome_perfil);

        campoNomePerfil = (EditText) findViewById(R.id.editText12);
        alterarFonte();
        clicarBotaoNomear();

    }

    private void alterarFonte() {
        Typeface fonte = Typeface.createFromAsset(getAssets(), "fonts/Chewy.ttf");
        campoNomePerfil.setTypeface(fonte);
    }

    private void clicarBotaoNomear() {
        botaoNomear = (Button) findViewById(R.id.button22);
        botaoNomear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verificarNomePerfil();
            }
        });
    }

    public void verificarNomePerfil() {
        nomePerfil = campoNomePerfil.getText().toString().trim();
        if (perfilService.verificarNomeIgual(nomePerfil)) {
            if (perfilService.verificarNomeVazio(nomePerfil)) {
                perfilUsuario.setNome(nomePerfil);
                salvarBD();
                finalizar();
            } else {
                erro = Toast.makeText(getApplicationContext(), R.string.campoVazio, Toast.LENGTH_SHORT);
                erro.show();
            }
        } else {
            erro = Toast.makeText(getApplicationContext(), R.string.perfilExiste, Toast.LENGTH_SHORT);
            erro.show();
        }
    }

    public void salvarBD() {
        ArrayList<PerfilUsuario> perfilUsuarioArrayList = LoginAct.getPessoa().getPerfilUsuarioArrayList();
        if (perfilUsuarioArrayList.size()==0) {
            LoginAct.getPessoa().setPerfilAtual(perfilUsuario);
            perfilService.adcPerfil(perfilUsuario, this);
            perfilService.adcPerfilFavorito(LoginAct.getPessoa().getId(),nomePerfil,this);
        }else{perfilService.adcPerfil(perfilUsuario, this);}
    }

    public void finalizar() {
        Intent intent = new Intent();
        intent.putExtra("nomePerfil", nomePerfil);
        setResult(1, intent);
        finish();
    }
}
