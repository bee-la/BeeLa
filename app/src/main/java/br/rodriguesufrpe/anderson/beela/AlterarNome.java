package br.rodriguesufrpe.anderson.beela;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Button;

public class AlterarNome extends AppCompatActivity {

    private TextView alterarNomeEditText3;
    private Button alterarNomeButton11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar_nome);

        //      ----------------------------------Mudar letra-------------------------------------------
        alterarNomeEditText3 = (TextView) findViewById(R.id.editText3);
        Typeface fonte1 = Typeface.createFromAsset(getAssets(), "fonts/Chewy.ttf");
        alterarNomeEditText3.setTypeface(fonte1);
    }
}
