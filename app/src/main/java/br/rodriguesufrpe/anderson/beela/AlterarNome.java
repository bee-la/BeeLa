package br.rodriguesufrpe.anderson.beela;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

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
        BDcomandos bd = new BDcomandos(this,"W");
        bd.updateNome(Login.usuario,alterarNome);
        Toast Sucesso;
        Sucesso = Toast.makeText(getApplicationContext(), "Nome Alterado", Toast.LENGTH_SHORT);
        Sucesso.show();
        startActivity(new Intent(AlterarNome.this, Login.class));
        //ATENÇÃO : Se botar volta para home criar um bug na alterações
        // salva mais o objeto ainda fica com as informações antiga tem que atualizar o objeto por isso
        // faço o usuario voltar para Tela Login

    }
//-------------------------------------------------------------------------------------------------------


    //-----------------------------------Validação do campo Nome-----------------------------------------------
    public boolean ehValidoAlterarNome() {
        boolean valido = true;
        if (alterarNome.isEmpty()) {
            alterarNomeEditText3.setError(getString(R.string.campoVazio));
            valido = false;
        }
        return valido;
    }
}
