package br.rodriguesufrpe.anderson.beela;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class AlterarSenha extends AppCompatActivity {

    private TextView senhaAtualEditText7;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar_senha);

        senhaAtualEditText7 = (TextView) findViewById(R.id.editText7);
        Typeface fonte1 = Typeface.createFromAsset(getAssets(), "fonts/Chewy.ttf");
        senhaAtualEditText7.setTypeface(fonte1);
    }
}
