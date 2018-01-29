package br.ufrpe.beela.usuario.gui;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import br.ufrpe.beela.gui.R;
import br.ufrpe.beela.perfil.dominio.PerfilUsuario;
import br.ufrpe.beela.usuario.negocio.Criptografia;
import br.ufrpe.beela.usuario.dominio.Pessoa;
import br.ufrpe.beela.usuario.dominio.Usuario;
import br.ufrpe.beela.usuario.negocio.UsuarioService;


public class LoginAct extends AppCompatActivity {
    private static Pessoa pessoa = new Pessoa();
    private UsuarioService usuarioValido = new UsuarioService();
    private TextView nomeApp, esqueceuSenha;
    private Button botaoEntrar, botaoCriarConta;
    private EditText campoEmail, campoSenha;
    private String email, senha;
    private Toast mensagemEsqSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        campoEmail = (EditText) findViewById(R.id.editText);
        campoSenha = (EditText) findViewById(R.id.editText2);
        nomeApp = (TextView) findViewById(R.id.textView);

        alterarFonte();
        clicarBotaoEntrar();
        irTelaCriarConta();
        irTelaEsqueceuSenha();
    }

    private void alterarFonte() {
        Typeface fonte = Typeface.createFromAsset(getAssets(), "fonts/Chewy.ttf");
        nomeApp.setTypeface(fonte);
        campoEmail.setTypeface(fonte);
        campoSenha.setTypeface(fonte);
    }

    private void clicarBotaoEntrar() {
        botaoEntrar = (Button) findViewById(R.id.button);
        botaoEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verificarLogin();
            }
        });
    }

    private void irTelaCriarConta() {
        botaoCriarConta = (Button) findViewById(R.id.button2);
        botaoCriarConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginAct.this, CriarContaAct.class));
            }
        });
    }

    private void irTelaEsqueceuSenha() {
        esqueceuSenha = (TextView) findViewById(R.id.textView3);
        esqueceuSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mensagemEsqSenha = Toast.makeText(getApplicationContext(), R.string.implementarFunc, Toast.LENGTH_SHORT);
                mensagemEsqSenha.show();
//                startActivity(new Intent(LoginAct.this, EsqueceuSenhaAct.class));
            }
        });
    }

    private void verificarLogin() {
        email = campoEmail.getText().toString().trim();
        senha = campoSenha.getText().toString().trim();
        if (verificarCampos()) {
            verificarEmailSenhaBD();
        }
    }

    private void verificarEmailSenhaBD() {
        email = campoEmail.getText().toString().trim();
        senha = campoSenha.getText().toString().trim();
        senha = Criptografia.criptografar(senha);
        Toast Erro;
        Erro = Toast.makeText(getApplicationContext(), R.string.erroNoLoginDoBanco, Toast.LENGTH_SHORT);

        if (usuarioValido.verificarEmailSenhaLogar(email, senha, this)) {
            iniciarSessão();
            irTelaHome();
            finish();
        } else {
            Erro.show();
        }
    }

    private void iniciarSessão() {
        Usuario usuario = usuarioValido.gerarUsuario(email, senha, this);
        pessoa = usuarioValido.gerarPessoa(usuario.getId(), this);
        PerfilUsuario perfilAtual = usuarioValido.gerarPerfilAtual(pessoa.getId(),this);
        pessoa.setPerfilUsuarioArrayList(usuarioValido.gerarPerfilUsuario(pessoa.getId(),this));
        pessoa.setPerfilAtual(perfilAtual);
        pessoa.setUsuario(usuario);
    }

    private void irTelaHome() {
        startActivity(new Intent(LoginAct.this, HomeAct.class));
    }

    private boolean verificarCampos() {
        if (usuarioValido.validarCampoEmail(email)) {
            campoEmail.setError(getString(R.string.emailInvalido));
        }
        if (usuarioValido.validarCampoVazio(senha)) {
            campoSenha.setError(getString(R.string.senhaInvalida));
        } else {
            return true;
        }
        return false;
    }

    public static Pessoa getPessoa() {
        return pessoa;
    }

//-------------------------------------------------------------------------------------------

}
