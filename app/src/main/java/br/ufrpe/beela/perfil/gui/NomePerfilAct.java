package br.ufrpe.beela.perfil.gui;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import br.ufrpe.beela.perfil.dominio.PerfilUsuario;
import br.ufrpe.beela.perfil.negocio.PerfilService;
import br.ufrpe.beela.usuario.gui.LoginAct;
import br.ufrpe.beela.gui.R;

public class NomePerfilAct extends AppCompatActivity {
    private PerfilUsuario perfilUsuario = new PerfilUsuario();
    private PerfilService perfilService = new PerfilService();
    private EditText campoNomePerfil;
    private Button botaoNomear;
    private Toast erro;
    private String nomePerfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nome_perfil);

        Bundle bundle = getIntent().getExtras();
        perfilUsuario = (PerfilUsuario) bundle.getSerializable("perfilUsuario");

        campoNomePerfil = findViewById(R.id.editText12);
        alterarFonte();
        clicarBotaoNomear();

    }

    private void alterarFonte() {
        Typeface fonte = Typeface.createFromAsset(getAssets(), "fonts/Chewy.ttf");
        campoNomePerfil.setTypeface(fonte);
    }

    private void clicarBotaoNomear() {
        botaoNomear = findViewById(R.id.button22);
        botaoNomear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verificarNomePerfil();
            }
        });
    }

    public void verificarNomePerfil() {
        nomePerfil = campoNomePerfil.getText().toString().trim();
        if(verificarNomeIgual(nomePerfil)){
            if (!nomePerfil.isEmpty()){
                perfilUsuario.setNome(nomePerfil);
                salvarBD();
                finish();
            }
            else {
                erro = Toast.makeText(getApplicationContext(), R.string.campoVazio, Toast.LENGTH_SHORT);
                erro.show();
            }
        } else {
            erro = Toast.makeText(getApplicationContext(), R.string.perfilExiste, Toast.LENGTH_SHORT);
            erro.show();
        }
    }


    public boolean verificarNomeIgual(String NomePerfil) {
        boolean saida = true;
        for (PerfilUsuario perfilUsuario : PerfilAct.getListaPerfis())
            if (perfilUsuario.getNome().equals(NomePerfil)) {
                saida = false;
                break;
            }
        return saida;
    }

    public void salvarBD() {

        if (perfilService.verificarPerfilAtualExiste(LoginAct.getPessoa().getId(),this)) {
            perfilService.adcPerfil(perfilUsuario, this);
            perfilService.adcComida(perfilUsuario,this);
            perfilService.adcMusica(perfilUsuario,this);
            perfilService.adcEsporte(perfilUsuario,this);
            perfilService.adcLugar(perfilUsuario,this);
            perfilService.adcPerfilFavorito(LoginAct.getPessoa().getId(),nomePerfil,this);
        }
        else{
            perfilService.adcPerfil(perfilUsuario, this);
            perfilService.adcComida(perfilUsuario,this);
            perfilService.adcMusica(perfilUsuario,this);
            perfilService.adcEsporte(perfilUsuario,this);
            perfilService.adcLugar(perfilUsuario,this);
        }
    }
}
