package br.ufrpe.beela.usuario.gui;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.*;

import java.util.ArrayList;

import br.ufrpe.beela.gui.R;
import br.ufrpe.beela.perfil.dominio.PerfilUsuario;
import br.ufrpe.beela.pessoa.negocio.PessoaService;
import br.ufrpe.beela.usuario.negocio.Criptografia;
import br.ufrpe.beela.pessoa.dominio.Pessoa;
import br.ufrpe.beela.usuario.dominio.Usuario;
import br.ufrpe.beela.usuario.negocio.UsuarioService;

import static br.ufrpe.beela.gui.R.id.textViewCriarConta;


public class LoginAct extends AppCompatActivity {

    private static Pessoa pessoa = new Pessoa();
    private Usuario usuario = new Usuario();
    private UsuarioService usuarioValido = new UsuarioService();
    private TextView nomeApp;
    private TextView esqueceuSenha;
    private TextView botaoCriarConta;
    private Button botaoEntrar;
    private EditText campoEmail;
    private EditText campoSenha;
    private String email, senha, senha2;
    private Toast mensagemEsqSenha;
    private PessoaService pessoaService = new PessoaService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        campoEmail = findViewById(R.id.editText);
        campoSenha = findViewById(R.id.editText2);
        nomeApp = findViewById(R.id.textView);

        verificarLembrarMim();
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
        botaoEntrar = findViewById(R.id.button);
        botaoEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verificarLogin();
            }
        });
    }

    private void irTelaCriarConta(){

        botaoCriarConta = findViewById(textViewCriarConta);
        botaoCriarConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginAct.this, CriarContaAct.class));
                verificarLembrarMim();
            }
        });
    }

    private void irTelaEsqueceuSenha() {
        esqueceuSenha = findViewById(R.id.textViewEsqueceuSenha);

        esqueceuSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mensagemEsqSenha = Toast.makeText(getApplicationContext(), R.string.implementarFunc, Toast.LENGTH_SHORT);
                mensagemEsqSenha.show();
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
        senha2 = campoSenha.getText().toString().trim();
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
        usuario = usuarioValido.gerarUsuario(email, senha, this);
        pessoa = pessoaService.gerarPessoa(usuario.getId(), this);
        PerfilUsuario perfilAtual = usuarioValido.gerarPerfilAtual(pessoa.getId(),this);
        pessoa.setPerfilUsuarioArrayList(usuarioValido.gerarPerfilUsuario(pessoa.getId(),this));
        pessoa.setPerfilAtual(perfilAtual);
        pessoa.setUsuario(usuario);
        if(usuarioValido.verificarLembrarLogin(this)){usuarioValido.salvarLembrarMim(email,senha2,this);}
    }

    private void irTelaHome() {
        usuarioValido.alterarLembrarMim(email,senha2,this);
        startActivity(new Intent(LoginAct.this, HomeAct.class));
    }

    private boolean verificarCampos() {
        if (validarCampoVazio(email) || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            campoEmail.setError(getString(R.string.emailInvalido));
        }
        if (validarCampoVazio(senha)) {
            campoSenha.setError(getString(R.string.senhaInvalida));
        } else {
            return true;
        }
        return false;
    }

    public boolean validarCampoVazio(String campo) {
        if (campo.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }
    public void verificarLembrarMim(){
        if(!usuarioValido.verificarLembrarLogin(this)) {
            ArrayList<String> campos = usuarioValido.getLembrarMim(this);
            if (!campos.isEmpty()) {
                campoEmail.setText(campos.get(0));
                campoSenha.setText(campos.get(1));
            }
        }
    }
    public static Pessoa getPessoa() {
        return pessoa;
    }


}


