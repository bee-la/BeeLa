package br.ufrpe.beela.usuario.gui;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.ufrpe.beela.gui.R;
import br.ufrpe.beela.usuario.dominio.Usuario;
import br.ufrpe.beela.usuario.negocio.Criptografia;
import br.ufrpe.beela.usuario.dao.UsuarioDAO;
import br.ufrpe.beela.usuario.negocio.UsuarioService;

public class AlterarSenhaAct extends AppCompatActivity {
    private Usuario usuario = LoginAct.getPessoa().getUsuario();
    private UsuarioService usuarioService = new UsuarioService();

    private Button botaoAlterarSenha;
    private Toast senhaAlterada, erro;
    private String senhaAtual, novaSenha, repetirSenha;
    private EditText campoSenha, campoNovaSenha, campoRepetirSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar_senha);

        campoSenha = (EditText) findViewById(R.id.editText7);
        campoNovaSenha = (EditText) findViewById(R.id.editText8);
        campoRepetirSenha = (EditText) findViewById(R.id.editText9);

        alterarFonte();
        clicarBotaoAlterar();

    }

    private void alterarFonte() {
        Typeface fonte = Typeface.createFromAsset(getAssets(), "fonts/Chewy.ttf");
        campoSenha.setTypeface(fonte);
        campoNovaSenha.setTypeface(fonte);
        campoRepetirSenha.setTypeface(fonte);
    }

    private void clicarBotaoAlterar() {
        botaoAlterarSenha = (Button) findViewById(R.id.button10);
        botaoAlterarSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verificarSenhaAtual();
            }
        });
    }

    public void verificarSenhaAtual() {
        senhaAtual = campoSenha.getText().toString().trim();
        if (Criptografia.criptografar(senhaAtual).equals(usuario.getSenha())) {
            novaSenha = campoNovaSenha.getText().toString().trim();
            repetirSenha = campoRepetirSenha.getText().toString().trim();

            if (validarCampos()) {
                alterarSenha();
            }
        } else {
            erro = Toast.makeText(getApplicationContext(), R.string.senhaAtualDiferente, Toast.LENGTH_SHORT);
            erro.show();
        }
    }

    public void alterarSenha() {
        UsuarioDAO bd = new UsuarioDAO();
        bd.getEscrever(this);
        bd.updateSenha(usuario, Criptografia.criptografar(novaSenha));
        usuario.setSenha(Criptografia.criptografar(novaSenha));
        limparCampos();
        senhaAlterada = Toast.makeText(getApplicationContext(), R.string.senhaAlterada, Toast.LENGTH_SHORT);
        senhaAlterada.show();
    }

    public boolean validarCampos() {
        if (usuarioService.validarCampoVazio(senhaAtual)) {
            campoSenha.setError(getString(R.string.campoVazio));
            return false;
        }

        if (usuarioService.verificarSenha(novaSenha)) {
            campoNovaSenha.setError(getString(R.string.senhaInvalida));
            return false;
        }

        if (!repetirSenha.equals(novaSenha)) {
            campoRepetirSenha.setError(getString(R.string.senhasDiferentes));
            return false;
        } else {
            return true;
        }
    }

    private void limparCampos() {
        campoSenha.setText("");
        campoNovaSenha.setText("");
        campoRepetirSenha.setText("");
    }

}
