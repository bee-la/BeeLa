package br.ufrpe.beela.gui.usuario;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import br.ufrpe.beela.gui.Login;
import br.ufrpe.beela.gui.R;
import br.ufrpe.beela.gui.home;
import br.ufrpe.beela.negociaçao.usuario.Criptografia;
import br.ufrpe.beela.dao.usuario.BDcomandosUsuario;

public class AlterarSenha extends AppCompatActivity {

    private TextView senhaAtualText7,novaSenhaText8, repetirSenhaText9;
    private Button alterarSenhaButton10;
    Toast senhaAlterada;
    private String senhaAtual, novaSenha, repetirSenha;
    private EditText senhaEditText7, novaSenhaEditText8, repetirSenhaEditText9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar_senha);

        senhaEditText7=(EditText)findViewById(R.id.editText7);
        novaSenhaEditText8=(EditText)findViewById(R.id.editText8);
        repetirSenhaEditText9=(EditText)findViewById(R.id.editText9);


//---------------------------------Alterar Fonte----------------------------------------------------
        Typeface fonte1 = Typeface.createFromAsset(getAssets(), "fonts/Chewy.ttf");
        senhaAtualText7 = (TextView) findViewById(R.id.editText7);
        senhaAtualText7.setTypeface(fonte1);

        novaSenhaText8 = (TextView) findViewById(R.id.editText8);
        novaSenhaText8.setTypeface(fonte1);

        repetirSenhaText9 = (TextView) findViewById(R.id.editText9);
        repetirSenhaText9.setTypeface(fonte1);


//----------------------------Validacao do clique do botao Alterar Senha----------------------------
        alterarSenhaButton10 = (Button) findViewById(R.id.button10);
        alterarSenhaButton10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validarCliqueAlterarSenha();
            }
        });
    }


//----------------------------------Validacao dos campos----------------------------------------

    public void validarCliqueAlterarSenha() {
        senhaAtual = senhaEditText7.getText().toString().trim();
        if (Criptografia.criptografar(senhaAtual).equals(Login.usuario.getSenha())) {
            novaSenha = novaSenhaEditText8.getText().toString().trim();
            repetirSenha = repetirSenhaEditText9.getText().toString().trim();

            if (ehValidoAlterarSenha()) {
                alterarSucessoSenha();
            }
        }
        else{
            Toast Erro;
            Erro = Toast.makeText(getApplicationContext(), R.string.senhaAtualDiferente, Toast.LENGTH_SHORT);
            Erro.show();
        }
    }

    public void alterarSucessoSenha(){
        senhaAlterada=Toast.makeText(getApplicationContext(), R.string.senhaAlterada, Toast.LENGTH_SHORT);
//TODO      O código que der certo se coloca aqui(Query do banco).
        BDcomandosUsuario bd = new BDcomandosUsuario(this,"W");
        bd.updateSenha(Login.usuario,Criptografia.criptografar(novaSenha));
        Login.usuario.setSenha(Criptografia.criptografar(novaSenha));
        senhaAlterada.show();
        finish();
//        startActivity(new Intent(AlterarSenha.this, home.class));
    }

    public boolean ehValidoAlterarSenha(){
        boolean valido=true;
        if(senhaAtual.isEmpty()){
            senhaEditText7.setError(getString(R.string.campoVazio));
            valido=false;
        }

        if(novaSenha.isEmpty()){
            novaSenhaEditText8.setError(getString(R.string.campoVazio));
            valido=false;
        }

        if(!repetirSenha.equals(novaSenha)){
            repetirSenhaEditText9.setError(getString(R.string.senhasDiferentes));
            valido=false;
        }

        return valido;
    }
//---------------------------------------------------------------------------------------------

}
