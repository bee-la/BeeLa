package br.ufrpe.beela.usuario.gui;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import br.ufrpe.beela.gui.R;
import br.ufrpe.beela.usuario.negocio.Criptografia;
import br.ufrpe.beela.usuario.dominio.Pessoa;
import br.ufrpe.beela.usuario.dominio.Usuario;
import br.ufrpe.beela.usuario.negocio.UsuarioService;


public class LoginAct extends AppCompatActivity implements View.OnClickListener {
    private static Usuario usuario = new Usuario();
    private static Pessoa pessoa = new Pessoa();
    private UsuarioService usuarioValido= new UsuarioService();
    private TextView nomeApp, esqueceuSenha, criarConta;
    private Button botaoEntrar, botaoCriarConta;
    private EditText campoEmail, campoSenha;
    private String email, senha;
    private Toast mensagemEsqSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        campoEmail=(EditText)findViewById(R.id.editText);
        campoSenha=(EditText)findViewById(R.id.editText2);
        nomeApp = (TextView) findViewById(R.id.textView);

        alterarFonte();
        criarBotoesTela();
    }

    private void criarBotoesTela() {
        botaoEntrar = (Button) findViewById(R.id.button);
        botaoEntrar.setOnClickListener(this);
        criarConta = (TextView) findViewById(R.id.textViewCriarConta);
        criarConta.setOnClickListener(this);
        esqueceuSenha=(TextView)findViewById(R.id.textViewEsqueceuSenha);
        esqueceuSenha.setOnClickListener(this);
    }


    public void onClick(View v){

        switch ((v.getId())){

            case R.id.button:
                verificarLogin();

                break;
            case R.id.textViewCriarConta:
                startActivity(new Intent(LoginAct.this, CriarContaAct.class));

                break;

            case R.id.textViewEsqueceuSenha:
                mensagemEsqSenha=Toast.makeText(getApplicationContext(), R.string.implementarFunc, Toast.LENGTH_SHORT);
                mensagemEsqSenha.show();




        }
    }


    private void alterarFonte(){
        Typeface fonte = Typeface.createFromAsset(getAssets(), "fonts/Chewy.ttf");
        nomeApp.setTypeface(fonte);
        campoEmail.setTypeface(fonte);
        campoSenha.setTypeface(fonte);
    }
//  Remover - Substituido pelo Switch Case
//    private void clicarBotaoEntrar(){
//        botaoEntrar = (Button) findViewById(R.id.button);
//        botaoEntrar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                verificarLogin();
//            }
//        });
//    }
//
//    private void irTelaCriarConta(){
//        criarConta = (TextView) findViewById(R.id.textViewCriarConta);
//        criarConta.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(LoginAct.this, CriarContaAct.class));
//            }
//        });
//    }
//
//    private void irTelaEsqueceuSenha(){
//        esqueceuSenha=(TextView)findViewById(R.id.textViewEsqueceuSenha);
//        esqueceuSenha.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mensagemEsqSenha=Toast.makeText(getApplicationContext(), R.string.implementarFunc, Toast.LENGTH_SHORT);
//                mensagemEsqSenha.show();
////                startActivity(new Intent(LoginAct.this, EsqueceuSenhaAct.class));
//            }
//        });
//    }

    private void verificarLogin() {
        email=campoEmail.getText().toString().trim();
        senha=campoSenha.getText().toString().trim();
        if(verificarCampos()){
            verificarEmailSenhaBD();
        }
    }

    private void verificarEmailSenhaBD(){
        email=campoEmail.getText().toString().trim();
        senha=campoSenha.getText().toString().trim();
        senha = Criptografia.criptografar(senha);
        Toast Erro;
        Erro = Toast.makeText(getApplicationContext(), R.string.erroNoLoginDoBanco, Toast.LENGTH_SHORT);

        if(usuarioValido.verificarEmailSenhaLogar(email, senha,this)){
            usuario = usuarioValido.gerarUsuario(email, senha,this);
            pessoa = usuarioValido.gerarPessoa(usuario.getId(),this);
            pessoa.setPerfil(usuarioValido.gerarPerfilUsuario(usuario.getId()));
            irTelaHome();
            finish();}

        else{
            Erro.show(); }
    }

    private void irTelaHome() {
        startActivity(new Intent(LoginAct.this, HomeAct.class));}

    private boolean verificarCampos(){
        if (usuarioValido.validarCampoEmail(email)){
            campoEmail.setError(getString(R.string.emailInvalido));
        }
        if (usuarioValido.validarCampoVazio(senha)){
            campoSenha.setError(getString(R.string.senhaInvalida));
        }
        else{
            return true;
        }
        return false;
    }

    public  static Usuario getUsuario(){
        return usuario;
    }
    public  static Pessoa getPessoa(){
        return pessoa;
    }

//-------------------------------------------------------------------------------------------

}

//-------------------------------------------------------------------------------------------


