package br.ufrpe.beela.gui;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.TextView;
import android.view.View;
import android.widget.*;

import br.ufrpe.beela.negociaçao.Criptografia;
import br.ufrpe.beela.gui.R;
import br.ufrpe.beela.dao.BDcomandosUsuario;
import br.ufrpe.beela.negociaçao.Usuario;

public class Login extends AppCompatActivity {
    public static Usuario usuario = new Usuario();
    private TextView t, EsqueceuTextView3;
    private Button criarContaButton2, entrarButton;

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
        startActivity(new Intent(Login.this, EsqueceuSenha.class));
    }

    private void criarContaButton2() {
        startActivity(new Intent(Login.this, CriarConta.class));
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
        BDcomandosUsuario bd = new BDcomandosUsuario(this,"R");
        //list = bd.buscar();
        String senhaCriptografada = Criptografia.criptografar(senha);

        Toast Erro;
        Erro = Toast.makeText(getApplicationContext(), R.string.erroNoLoginDoBanco, Toast.LENGTH_SHORT);

//        for (int i = 0; list.size()>i; i++ ){
//            if(email.equals(list.get(i).getEmail()) && senhaCriptografada.equals(list.get(i).getSenha()))  {
//                loga=true; }
//        }
//        if(loga==true){
        if(bd.buscarVLogin(email, senhaCriptografada)){
            usuario = bd.sqlRetornaObjetoUsuario(email, senhaCriptografada);
            entrarHome(); }
        else{
            Erro.show(); }
    }

    private void entrarHome() {
        startActivity(new Intent(Login.this, home.class));}

    public boolean ehValidoLogin(){
        boolean valido=true;
        if(email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError(getString(R.string.emailInvalido));
            valido=false;
        }
        if(senha.isEmpty()){
            editText2Senha.setError(getString(R.string.senhaInvalida));
        }
        return valido;
    }
//-------------------------------------------------------------------------------------------

}
