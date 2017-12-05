package br.rodriguesufrpe.anderson.beela;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.TextView;
import android.view.View;
import android.widget.*;

public class Login extends AppCompatActivity {
    private TextView t;
    Button criarContaButton2, entrarButton;

    private EditText editTextEmail, editText2Senha;
    private String email, senha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextEmail=(EditText)findViewById(R.id.editText);
        editText2Senha=(EditText)findViewById(R.id.editText2);

        //        Mudar fonte
        t = (TextView) findViewById(R.id.textView);
        Typeface fonte = Typeface.createFromAsset(getAssets(), "fonts/Chewy.ttf");
        t.setTypeface(fonte);

//--------------------------Função trocar tela para Criar Conta------------------------------------------
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
        // TODO what will go after the valid  input
    }

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

    public void clear(View v) {
        editTextEmail.setText("");
        editTextEmail.setText("");
    }


}
