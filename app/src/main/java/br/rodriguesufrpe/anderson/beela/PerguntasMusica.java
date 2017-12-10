package br.rodriguesufrpe.anderson.beela;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class PerguntasMusica extends AppCompatActivity {
    private TextView tipoMusicaTextView9;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perguntas_musica);

        Typeface fonte = Typeface.createFromAsset(getAssets(), "fonts/Chewy.ttf");

        tipoMusicaTextView9 = (TextView) findViewById(R.id.textView9);
        tipoMusicaTextView9.setTypeface(fonte);
    }
}
