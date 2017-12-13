package br.ufrpe.beela.gui.usuario;

import android.content.Intent;
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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import br.ufrpe.beela.gui.Login;
import br.ufrpe.beela.gui.R;
import br.ufrpe.beela.negociaçao.usuario.Criptografia;
import br.ufrpe.beela.dao.usuario.BDcomandosUsuario;
import br.ufrpe.beela.negociaçao.usuario.Usuario;

public class CriarConta extends AppCompatActivity {
//    private TextView textViewNome, textViewCelular,  textViewEmail, textViewSenha, textViewRepetirSenha;
    private Button criarButton3;
    private Toast contaCriada;
    private boolean existeCelularEmail=false;

    private String nomeValidar, celularValidar, emailValidar, senhaValidar, repetirSenhaValidar;
    private EditText editText4Nome, editText5Celular, editText6Email, editText10Senha, editText11RepetirSenha;
    private ArrayList<TextView> textos= new ArrayList<TextView>();

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_conta);

        editText4Nome=(EditText)findViewById(R.id.editText4);
        editText5Celular=(EditText)findViewById(R.id.editText5);
        editText6Email=(EditText)findViewById(R.id.editText6);
        editText10Senha=(EditText)findViewById(R.id.editText10);
        editText11RepetirSenha=(EditText)findViewById(R.id.editText11);


//-----------------------------------Alteração da fonte-------------------------------------------
        textos.add((TextView) findViewById(R.id.editText4));
        textos.add((TextView) findViewById(R.id.editText5));
        textos.add((TextView) findViewById(R.id.editText6));
        textos.add((TextView) findViewById(R.id.editText10));
        textos.add((TextView) findViewById(R.id.editText11));


        Typeface fonte1 = Typeface.createFromAsset(getAssets(), "fonts/Chewy.ttf");
        for (TextView txtView: textos) {
            txtView.setTypeface(fonte1);
        }

//----------------------------Validacao do clique do botao Criar------------------------------------------
        criarButton3 = (Button) findViewById(R.id.button3);
        criarButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validarCliqueCriar();
            }
        });
    }


    //----------------------------------Validacao dos campos----------------------------------------
    public void validarCliqueCriar(){
        nomeValidar=editText4Nome.getText().toString().trim();
        celularValidar=editText5Celular.getText().toString().trim();
        emailValidar=editText6Email.getText().toString().trim();
        senhaValidar=editText10Senha.getText().toString().trim();
        repetirSenhaValidar=editText11RepetirSenha.getText().toString().trim();

        if(ehValidoCriarConta()){
            sucessoCriarConta();
        }
    }

    public void sucessoCriarConta(){
        //TODO fazer busca
        //iniciarFirebase();
        verificarEmailCelular();
        if (existeCelularEmail==false){
            salvarBanco();
            limparCampos();
            contaCriada = Toast.makeText(getApplicationContext(), R.string.contaCriada, Toast.LENGTH_SHORT);
            contaCriada.show();
            existeCelularEmail=true;
            startActivity(new Intent(CriarConta.this, Login.class));
        }
        else {
            Toast erro;
            erro = Toast.makeText(getApplicationContext(), R.string.emailCelularExiste, Toast.LENGTH_SHORT);
            erro.show();
        }
    }

    private void limparCampos() {

        editText6Email.setText("");
        editText4Nome.setText("");
        editText10Senha.setText("");
        editText11RepetirSenha.setText("");
        editText5Celular.setText("");
    }

    public boolean ehValidoCriarConta(){
        boolean valido=true;
        if(nomeValidar.isEmpty()){
            editText4Nome.setError(getString(R.string.campoVazio));
            valido=false;
        }

        if(celularValidar.length()<9 || !Patterns.PHONE.matcher(celularValidar).matches()){
            editText5Celular.setError(getString(R.string.celularInvalido));
            valido=false;
        }

        if(emailValidar.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(emailValidar).matches()){
            editText6Email.setError(getString(R.string.emailInvalido));
            valido=false;
        }

        if(senhaValidar.isEmpty()){
            editText10Senha.setError(getString(R.string.campoVazio));
            valido=false;
        }

        if (repetirSenhaValidar.isEmpty()){
            editText11RepetirSenha.setError(getString(R.string.campoVazio));
            valido=false;
        }

        if (!repetirSenhaValidar.equals(senhaValidar)){
            editText11RepetirSenha.setError(getString(R.string.senhasDiferentes));
            valido=false;
        }

        return valido;
    }

    public Usuario criarObjetoPessoa(){

        editText6Email = (EditText)findViewById(R.id.editText6);
        editText4Nome = (EditText)findViewById(R.id.editText4);
        editText10Senha = (EditText)findViewById(R.id.editText10);
        editText5Celular = (EditText)findViewById(R.id.editText5);

        Usuario u = new Usuario();
        u.setCelular(editText5Celular.getText().toString());
        u.setEmail(editText6Email.getText().toString());
        u.setNome(editText4Nome.getText().toString());
        u.setSenha(editText10Senha.getText().toString());
        u.setSenha(Criptografia.criptografar(u.getSenha()));

        return u;
    }
    public void salvarBanco(){
        BDcomandosUsuario bd = new BDcomandosUsuario(this,"W");
        bd.inserir(criarObjetoPessoa());
    }

    public void verificarEmailCelular(){
        String email=editText6Email.getText().toString().trim();
        String celular=editText5Celular.getText().toString().trim();

        BDcomandosUsuario bd = new BDcomandosUsuario(this,"R");
        if (bd.buscarVEmail(email,celular)){
            existeCelularEmail=true;
        }
        else {
            existeCelularEmail=false;
        }
    }


}


