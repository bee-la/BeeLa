package br.ufrpe.beela.usuario.gui;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.TextView;
import android.view.View;
import android.widget.*;

import br.ufrpe.beela.gui.R;
import br.ufrpe.beela.usuario.dao.Criptografia;
import br.ufrpe.beela.usuario.dao.UsuarioDAO;
import br.ufrpe.beela.usuario.dominio.Usuario;
import br.ufrpe.beela.usuario.negocio.UsuarioService;

public class LoginAct extends AppCompatActivity {
    public static Usuario usuario = new Usuario();
    private UsuarioService usuarioValido= new UsuarioService();

    private TextView t, EsqueceuTextView3;
    private Button criarContaButton2, entrarButton;

    boolean valido=false;

    private EditText editTextEmail, editText2Senha;
    private String email, senha;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextEmail=(EditText)findViewById(R.id.editText);
        editText2Senha=(EditText)findViewById(R.id.editText2);

        /* TODO:        Mudar fonte */
        t = (TextView) findViewById(R.id.textView);
        Typeface fonte = Typeface.createFromAsset(getAssets(), "fonts/Chewy.ttf");
        t.setTypeface(fonte);

//--------------------------Trocar tela para Criar Conta------------------------------------------
        entrarButton = (Button) findViewById(R.id.button);
        entrarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                entrarSucessoLogin();
            }
        });

        EsqueceuTextView3=(TextView)findViewById(R.id.textView3);
        EsqueceuTextView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                esqueceuSenha();
            }
        });

        criarContaButton2 = (Button) findViewById(R.id.button2);
        criarContaButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                criarContaButton2();
            }
        });



//------------------------------Validacao do clique do botao Entrar---------------------------
        entrarButton=(Button) findViewById(R.id.button);
        entrarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validarCliqueEntrar();
            }
        });

    }

//-----------------------------------------Métodos para trocar de tela--------------------------------------------
    private void esqueceuSenha() {
        startActivity(new Intent(LoginAct.this, EsqueceuSenhaAct.class));
    }

    private void criarContaButton2() {
        startActivity(new Intent(LoginAct.this, CriarContaAct.class));
    }


    //----------------------------------Validacao dos campos----------------------------------------
    private void validarCliqueEntrar() {
        email=editTextEmail.getText().toString().trim();
        senha=editText2Senha.getText().toString().trim();
        if(ehValidoLogin()){
            entrarSucessoLogin();
        }
    }

    public void entrarSucessoLogin(){
        email=editTextEmail.getText().toString().trim();
        senha=editText2Senha.getText().toString().trim();
        UsuarioDAO bd = new UsuarioDAO(this,"R");
        //list = bd.buscar();
        String senhaCriptografada = Criptografia.criptografar(senha);
        Toast Erro;
        Erro = Toast.makeText(getApplicationContext(), R.string.erroNoLoginDoBanco, Toast.LENGTH_SHORT);

        if(bd.buscarVLogin(email, senhaCriptografada)){

//TODO      Esse objeto não é necessário
//            Usuario usuario = new Usuario();
//            usuario = bd.sqlRetornaObjetoUsuario(email, senhaCriptografada);
            entrarHome(); }
        else{
            Erro.show(); }
    }

    private void entrarHome() {
        startActivity(new Intent(LoginAct.this, HomeAct.class));}

    public boolean ehValidoLogin(){
//TODO      Mudança na lógica. Agora funcionando
        if (usuarioValido.validarCamposEmail(email)){
            editTextEmail.setError(getString(R.string.emailInvalido));
//            valido=false;
        }
        if (usuarioValido.validarCamposVazio(senha)){
            editText2Senha.setError(getString(R.string.senhaInvalida));
//            valido=false;
        }
        else{
            valido=true;
        }

//        if(email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
//            editTextEmail.setError(getString(R.string.emailInvalido));
//            valido=false;
//        }
//        if(senha.isEmpty()){
//            editText2Senha.setError(getString(R.string.senhaInvalida));
//        }
        return valido;
    }
//-------------------------------------------------------------------------------------------

}
