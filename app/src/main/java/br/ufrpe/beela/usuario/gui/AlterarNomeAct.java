package br.ufrpe.beela.usuario.gui;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

import br.ufrpe.beela.gui.R;
import br.ufrpe.beela.usuario.dominio.Pessoa;
import br.ufrpe.beela.usuario.negocio.UsuarioService;

public class AlterarNomeAct extends AppCompatActivity {
    private Pessoa pessoa = LoginAct.getPessoa();
    private UsuarioService usuarioService = new UsuarioService();
    private Toast nomeAlterado;
    private Button botaoAlterarNome;
    private String nome;
    private EditText alterarNome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar_nome);

        alterarNome = (EditText) findViewById(R.id.editText3);

        alterarFonte();
        clicarBotaoAlterar();

    }

    private void alterarFonte() {
        Typeface fonte = Typeface.createFromAsset(getAssets(), "fonts/Chewy.ttf");
        alterarNome.setTypeface(fonte);
    }

    private void clicarBotaoAlterar() {
        botaoAlterarNome = (Button) findViewById(R.id.button11);
        botaoAlterarNome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verificarNome();
            }
        });
    }

    private void verificarNome() {
        if (validarCampo()) {
            usuarioService.alterarNome(pessoa, nome, this);
            alterarNome.setText("");
            nomeAlterado = Toast.makeText(getApplicationContext(), R.string.nomeAlterado, Toast.LENGTH_SHORT);
            nomeAlterado.show();
        }
    }

    private boolean validarCampo() {
        nome = alterarNome.getText().toString().trim();
        if (nome.isEmpty()) {
            alterarNome.setError(getString(R.string.campoVazio));
            return false;
        } else {
            return true;
        }
    }
}
