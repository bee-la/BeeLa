package br.rodriguesufrpe.anderson.beela;

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

public class AlterarSenha extends AppCompatActivity {

    private TextView senhaAtualText7;
    private Button alterarSenhaButton10;

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
//--------------------------------------------------------------------------------------------------


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
            Erro = Toast.makeText(getApplicationContext(), "SenhaAtual Diferente", Toast.LENGTH_SHORT);
            Erro.show();
        }
    }

    public void alterarSucessoSenha(){
        // TODO código que der certo se coloca aqui(Query do banco). Validar se o email e celular já estão cadastrados.
        BDcomandos bd = new BDcomandos(this,"W");
        bd.updateSenha(Login.usuario,Criptografia.criptografar(novaSenha));
        Toast Sucesso;
        Sucesso = Toast.makeText(getApplicationContext(), "Senha Alterado", Toast.LENGTH_SHORT);
        Sucesso.show();
        startActivity(new Intent(AlterarSenha.this, Login.class));
        //ATENÇÃO : Se botar volta para home criar um bug na alterações
        // salva mais o objeto ainda fica com as informações antiga tem que atualizar o objeto por isso
        // faço o usuario voltar para Tela Login
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
