package br.rodriguesufrpe.anderson.beela;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;

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

//      ----------------------------------Mudar fonte-------------------------------------------
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
        // TODO what will go after the valid  input
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
