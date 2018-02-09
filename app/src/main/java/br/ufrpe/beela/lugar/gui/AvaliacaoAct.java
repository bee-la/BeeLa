package br.ufrpe.beela.lugar.gui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import br.ufrpe.beela.gui.R;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

public class AvaliacaoAct extends AppCompatActivity {

    private RatingBar ratingBar;
    private TextView txtValorAvaliacao;
    private Button btnSubmit;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avaliacao);
        addListenerOnRatingBar();
        addListenerOnButton();
    }

    public void addListenerOnRatingBar() {
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        txtValorAvaliacao = (TextView) findViewById(R.id.txtValorAvaliacao);
        ratingBar.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float avaliacao, boolean fromUser) {
                txtValorAvaliacao.setText(String.valueOf(avaliacao));
            }
        });
    }

    public void addListenerOnButton() {
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AvaliacaoAct.this,
                        String.valueOf("Voto computado: " + ratingBar.getRating()),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void addVoto(int idPeessoa,int idLugar,int nota){

    }
}









