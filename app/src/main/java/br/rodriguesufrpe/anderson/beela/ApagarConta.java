package br.rodriguesufrpe.anderson.beela;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ApagarConta extends AppCompatActivity {
    private TextView textButton9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apagar_conta);

        textButton9 = (TextView) findViewById(R.id.button9);
        Typeface fonte1 = Typeface.createFromAsset(getAssets(), "fonts/Chewy.ttf");
        textButton9.setTypeface(fonte1);
    }
}
