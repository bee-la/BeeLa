package br.ufrpe.beela.usuario.gui;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import br.ufrpe.beela.gui.R;
import br.ufrpe.beela.usuario.dominio.Pessoa;
import br.ufrpe.beela.usuario.negocio.UsuarioService;

public class AlterarNomeAct extends AppCompatActivity {
    private Pessoa pessoa = LoginAct.getPessoa();
    private UsuarioService usuarioService = new UsuarioService();

    private TextView alterarNomeText3;
    private Button alterarNomeButton11;

    private String alterarNome;
    private EditText alterarNomeEditText3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar_nome);

        alterarNomeEditText3=(EditText)findViewById(R.id.editText3);

//------------------------------------------Mudar Fonte-------------------------------------------
        alterarNomeText3 = (TextView) findViewById(R.id.editText3);
        Typeface fonte1 = Typeface.createFromAsset(getAssets(), "fonts/Chewy.ttf");
        alterarNomeText3.setTypeface(fonte1);
        alterarNome=alterarNomeEditText3.getText().toString().trim();

//-----------------------------------Validação clique botão Alterar Nome--------------------------------
        alterarNomeButton11 = (Button) findViewById(R.id.button11);
        alterarNomeButton11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validarCliqueAlterarNome();
            }
        });
    }

    public void validarCliqueAlterarNome(){
        alterarNome=alterarNomeEditText3.getText().toString().trim();
        if(ehValidoAlterarNome()) {
            usuarioService.alterarSucessoNome(pessoa,alterarNome,this);
//            startActivity(new Intent(AlterarNomeAct.this, HomeAct.class));
            alterarNomeEditText3.setText("");
            Toast Sucesso;
            Sucesso = Toast.makeText(getApplicationContext(), "Nome Alterado", Toast.LENGTH_SHORT);
            Sucesso.show();
        }
    }
//-------------------------------------------------------------------------------------------------------

    //-----------------------------------Validação do campo Nome-----------------------------------------------
    public boolean ehValidoAlterarNome() {
        alterarNome=alterarNomeEditText3.getText().toString().trim();
        if (usuarioService.validarCamposVazio(alterarNome)) {
            alterarNomeEditText3.setError(getString(R.string.campoVazio));
            return false;}
        if (usuarioService.validarNomeIgual(alterarNome, pessoa)) {
            alterarNomeEditText3.setError("Nome Iguais");
            return false;}
        else {
            return true;
        }
    }
}
