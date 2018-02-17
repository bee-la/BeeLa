package br.ufrpe.beela.usuario.gui;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import br.ufrpe.beela.gui.R;
import br.ufrpe.beela.pessoa.negocio.PessoaService;
import br.ufrpe.beela.usuario.dominio.Usuario;
import br.ufrpe.beela.usuario.negocio.Criptografia;
import br.ufrpe.beela.usuario.negocio.UsuarioService;


public class CriarContaAct extends AppCompatActivity {

    private Button botaoCriar;
    private Toast contaCriada;
    private String nome, celular, email, senha, repetirSenha;
    private EditText campoNome, campoCelular, campoEmail, campoSenha, campoRepetirSenha;
    private ArrayList<TextView> textoCampos = new ArrayList<TextView>();
    private UsuarioService usuarioValido = new UsuarioService();
    private PessoaService pessoaService = new PessoaService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_conta);

        campoNome = (EditText) findViewById(R.id.editText4);
        campoCelular = (EditText) findViewById(R.id.editText5);
        campoEmail = (EditText) findViewById(R.id.editText6);
        campoSenha = (EditText) findViewById(R.id.editText10);
        campoRepetirSenha = (EditText) findViewById(R.id.editText11);

        alterarFonte();
        clicarBotaoCriar();

    }

    private void alterarFonte() {
        adcListaTexto();
        Typeface fonte = Typeface.createFromAsset(getAssets(), "fonts/Chewy.ttf");
        for (TextView txtView : textoCampos) {
            txtView.setTypeface(fonte);
        }
    }

    private void adcListaTexto() {
        textoCampos.add((TextView) findViewById(R.id.editText4));
        textoCampos.add((TextView) findViewById(R.id.editText5));
        textoCampos.add((TextView) findViewById(R.id.editText6));
        textoCampos.add((TextView) findViewById(R.id.editText10));
        textoCampos.add((TextView) findViewById(R.id.editText11));
    }

    private void clicarBotaoCriar() {
        botaoCriar = (Button) findViewById(R.id.button3);
        botaoCriar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verificarConta();
            }
        });
    }

    private void verificarConta() {
        nome = campoNome.getText().toString().trim();
        celular = campoCelular.getText().toString().trim();
        email = campoEmail.getText().toString().trim();
        senha = campoSenha.getText().toString().trim();
        repetirSenha = campoRepetirSenha.getText().toString().trim();
        if (verificarCampos()) {
            verificarEmailCelularBD();
        }
    }

    private void verificarEmailCelularBD() {
        if (usuarioValido.verificarEmailExiste(email, this)) {
            Toast erro;
            erro = Toast.makeText(getApplicationContext(), R.string.emailExiste, Toast.LENGTH_SHORT);
            erro.show();
        } else if (usuarioValido.verificarCelularExiste(celular, this)) {
            Toast erro2;
            erro2 = Toast.makeText(getApplicationContext(), R.string.celularExiste, Toast.LENGTH_SHORT);
            erro2.show();
        } else {
            usuarioValido.salvarUsuarioBancoDados(usuarioValido.criarUsuario(email, senha),  this);
            contaCriada = Toast.makeText(getApplicationContext(), R.string.contaCriada, Toast.LENGTH_SHORT);
            contaCriada.show();

            pessoaService.salvarPessoaBancoDados(usuarioValido.gerarUsuario(email,Criptografia.criptografar(senha),this),usuarioValido.criarPessoa(nome, celular),this);
            finish();
        }
    }

    private boolean verificarCampos(){
        if (validarCampoVazio(nome)) {
            campoNome.setError(getString(R.string.campoVazio));
            return false;
        }
        else if (celular.length() < 9 || celular.length() > 12 || !Patterns.PHONE.matcher(celular).matches()) {
            campoCelular.setError(getString(R.string.celularInvalido));
            return false;
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            campoEmail.setError(getString(R.string.emailInvalido));
            return false;
        }
        else if (validarCampoVazio(email)) {
            campoEmail.setError(getString(R.string.campoVazio));
            return false;
        }
        else if (senha.length() < 6 || senha.isEmpty()){
            campoSenha.setError(getString(R.string.senhaInvalida));
            return false;
        }

        else if (validarCampoVazio(repetirSenha)) {
            campoRepetirSenha.setError(getString(R.string.campoVazio));
            return false;
        }
        else if (!repetirSenha.equals(senha)) {
            campoRepetirSenha.setError(getString(R.string.senhasDiferentes));
            return false;
        }
        else{
            return true;
        }
    }


    public boolean validarCampoVazio(String campo) {
        if (campo.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }
}


