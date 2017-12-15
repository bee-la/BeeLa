package br.ufrpe.beela.usuario.gui;

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

import br.ufrpe.beela.gui.R;
import br.ufrpe.beela.usuario.dao.Criptografia;
import br.ufrpe.beela.usuario.dao.UsuarioDAO;
import br.ufrpe.beela.usuario.dominio.Usuario;
import br.ufrpe.beela.usuario.negocio.UsuarioService;

public class CriarContaAct extends AppCompatActivity {

    private Button criarButton3;
    private Toast contaCriada;

    boolean valido=false;

    private String nomeValidar, celularValidar, emailValidar, senhaValidar, repetirSenhaValidar;
    private EditText editText4Nome, editText5Celular, editText6Email, editText10Senha, editText11RepetirSenha;
    private ArrayList<TextView> textos= new ArrayList<TextView>();

    private UsuarioService usuarioValido = new UsuarioService();

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
            public void onClick(View v) {validarCliqueCriar();}
        });
    }

    //----------------------------------Validacao dos campos----------------------------------------
    public void validarCliqueCriar() {
        nomeValidar = editText4Nome.getText().toString().trim();
        celularValidar = editText5Celular.getText().toString().trim();
        emailValidar = editText6Email.getText().toString().trim();
        senhaValidar = editText10Senha.getText().toString().trim();
        repetirSenhaValidar = editText11RepetirSenha.getText().toString().trim();
        if(ehValidoCriarConta()) {
            sucessoCriarConta();
            }
        }

    public void sucessoCriarConta(){
        //TODO fazer busca
        if (usuarioValido.verificarEmailCelular(emailValidar,celularValidar,this)){
            Toast erro;
            erro = Toast.makeText(getApplicationContext(), R.string.emailCelularExiste, Toast.LENGTH_SHORT);
            erro.show();
        }
        else {
            usuarioValido.salvarBanco(usuarioValido.criarObjetoPessoa(emailValidar,nomeValidar,senhaValidar,celularValidar),this);
            limparCampos();
            contaCriada = Toast.makeText(getApplicationContext(), R.string.contaCriada, Toast.LENGTH_SHORT);
            contaCriada.show();
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
        if (usuarioValido.validarCamposVazio(nomeValidar)) {
            editText4Nome.setError(getString(R.string.campoVazio));
        }
        if (usuarioValido.validarCamposCelular(celularValidar)) {
            editText5Celular.setError(getString(R.string.celularInvalido));
        }
        if (usuarioValido.validarCamposEmail(emailValidar)) {
            editText6Email.setError(getString(R.string.emailInvalido));
        }
        if (usuarioValido.validarCamposVazio(senhaValidar)) {
            editText10Senha.setError(getString(R.string.campoVazio));
        }
        if (usuarioValido.validarCamposVazio(repetirSenhaValidar)) {
            editText11RepetirSenha.setError(getString(R.string.campoVazio));
        }
        if (!repetirSenhaValidar.equals(senhaValidar)) {
            editText11RepetirSenha.setError(getString(R.string.senhasDiferentes));
        }
        else{
            valido=true;
            }
        return valido;
    }
}


