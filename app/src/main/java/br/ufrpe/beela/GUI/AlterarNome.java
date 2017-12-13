package br.ufrpe.beela.GUI;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import br.ufrpe.beela.GUI.R;
import br.ufrpe.beela.DAO.BDcomandosUsuario;

public class AlterarNome extends AppCompatActivity {
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
        if(ehValidoAlterarNome()){
            alterarSucessoNome();
        }
    }

    public void alterarSucessoNome(){
        BDcomandosUsuario bd = new BDcomandosUsuario(this,"W");
        bd.updateNome(Login.usuario,alterarNome);
        Login.usuario.setNome(alterarNome);
        Toast Sucesso;
        Sucesso = Toast.makeText(getApplicationContext(), "Nome Alterado", Toast.LENGTH_SHORT);
        Sucesso.show();
        startActivity(new Intent(AlterarNome.this, home.class));


    }
//-------------------------------------------------------------------------------------------------------


    //-----------------------------------Validação do campo Nome-----------------------------------------------
    public boolean ehValidoAlterarNome() {
        boolean valido = true;
        if (alterarNome.isEmpty()) {
            alterarNomeEditText3.setError(getString(R.string.campoVazio));
            valido = false;
        }
        else if(alterarNome.equals(Login.usuario.getNome())){
            alterarNomeEditText3.setError("Nome Iguais");
            valido = false;
        }
        return valido;
    }
}
