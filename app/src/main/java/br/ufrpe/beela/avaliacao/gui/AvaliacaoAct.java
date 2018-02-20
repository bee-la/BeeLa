package br.ufrpe.beela.avaliacao.gui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import br.ufrpe.beela.avaliacao.negocio.AvaliacaoService;
import br.ufrpe.beela.gui.R;
import br.ufrpe.beela.lugar.dominio.Lugar;
import br.ufrpe.beela.lugar.negocio.LugarService;
import br.ufrpe.beela.pessoa.dominio.Pessoa;
import br.ufrpe.beela.usuario.gui.LoginAct;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

public class AvaliacaoAct extends AppCompatActivity {

    private Pessoa pessoa = LoginAct.getPessoa();
    private RatingBar ratingBar;
    private TextView txtValorAvaliacao;
    private Button btnSubmit;
    private Lugar lugarRecuperado;
    private Bundle bundle;
    private LugarService lugarService= new LugarService();

    private AvaliacaoService avaliacaoService = new AvaliacaoService();

    private double nota;

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
                nota=Double.valueOf(avaliacao);
            }
        });
    }

    public void addListenerOnButton() {
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                addVoto(pessoa.getId(),regraDeTres(nota));
                Toast.makeText(AvaliacaoAct.this, String.valueOf(getString(R.string.votoComputado) + nota), Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    public void addVoto(int idPessoa,double nota){
        avaliacaoService.addVoto(idPessoa,getLugarRecuperado(),nota,this);
    }

    public Double regraDeTres(double notaParaFormatar){
        double notaFinal=((int) notaParaFormatar)/5;
        return (notaFinal);
    }

    public Lugar getLugarRecuperado(){
        bundle = getIntent().getExtras();
        lugarRecuperado = (Lugar) bundle.getSerializable(getString(R.string.lugar));
        return lugarRecuperado;
    }

}









