package br.ufrpe.beela.usuario.gui;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import br.ufrpe.beela.gui.R;
import br.ufrpe.beela.usuario.dao.Criptografia;
import br.ufrpe.beela.usuario.dao.UsuarioDAO;
import br.ufrpe.beela.usuario.dominio.Usuario;
import br.ufrpe.beela.usuario.negocio.UsuarioService;

public class AlterarSenhaAct extends AppCompatActivity {

    private UsuarioService usuarioService = new UsuarioService();

    private TextView senhaAtualText7,novaSenhaText8, repetirSenhaText9;
    private Button alterarSenhaButton10;
    private Toast senhaAlterada;
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
        if (Criptografia.criptografar(senhaAtual).equals(LoginAct.getUsuario().getSenha())) {
            novaSenha = novaSenhaEditText8.getText().toString().trim();
            repetirSenha = repetirSenhaEditText9.getText().toString().trim();

            if (ehValidoAlterarSenha()) {
                alterarSucessoSenha();}}
        else{
            Toast Erro;
            Erro = Toast.makeText(getApplicationContext(), R.string.senhaAtualDiferente, Toast.LENGTH_SHORT);
            Erro.show();
        }
    }

    public void alterarSucessoSenha(){
        senhaAlterada=Toast.makeText(getApplicationContext(), R.string.senhaAlterada, Toast.LENGTH_SHORT);
        UsuarioDAO bd = new UsuarioDAO();
        bd.getEscrever(this);
        bd.updateSenha(LoginAct.getUsuario(),Criptografia.criptografar(novaSenha));
        LoginAct.getUsuario().setSenha(Criptografia.criptografar(novaSenha));
        senhaAlterada.show();
        finish();
        startActivity(new Intent(AlterarSenhaAct.this, HomeAct.class));
    }

    public boolean ehValidoAlterarSenha(){
        if(usuarioService.validarCamposVazio(senhaAtual)){
            senhaEditText7.setError(getString(R.string.campoVazio));
            return false;
        }

        if(usuarioService.validarCamposVazio(novaSenha)){
            novaSenhaEditText8.setError(getString(R.string.campoVazio));
            return false;
        }

        if(!repetirSenha.equals(novaSenha)){
            repetirSenhaEditText9.setError(getString(R.string.senhasDiferentes));
            return false;
        }
        else{
        return true;
        }
    }
//---------------------------------------------------------------------------------------------

}
