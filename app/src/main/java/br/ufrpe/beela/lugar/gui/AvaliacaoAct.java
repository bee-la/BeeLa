package br.ufrpe.beela.lugar.gui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import br.ufrpe.beela.gui.R;
import br.ufrpe.beela.lugar.dominio.Lugar;
import br.ufrpe.beela.usuario.dao.UsuarioDAO;
import br.ufrpe.beela.usuario.dominio.Pessoa;
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
    private Lugar recuperado;
    private Bundle bundle;

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

//TODO falta passar o lugar para essa tela
                //addVoto(pessoa.getId(),lugar.getId(),regraDeTres(ratingBar.getRating()));
                pessoa.setVotou();
                Toast.makeText(AvaliacaoAct.this,
                        String.valueOf("Voto computado: " + ratingBar.getRating()),
                        Toast.LENGTH_SHORT).show();

            }
        });
    }
    public void addVoto(int idPessoa,int idLugar,int nota){
        UsuarioDAO bd = new UsuarioDAO();
        bd.getLer(this);
        if(bd.verificarVoto(idPessoa,idLugar)){
            bd = new UsuarioDAO();
            bd.getEscrever(this);
            bd.setNota(idPessoa,idLugar,nota);
        }
        else{
            bd = new UsuarioDAO();
            bd.getEscrever(this);
            bd.updateNota(idPessoa,idLugar,nota);}
    }
    public Double regraDeTres(RatingBar ratingBar){
        return Double.valueOf(ratingBar.getRating());

//        int notaDadaRating=(int) ratingBar.getRating();
//        double notaFinal=notaDadaRating/5;
//        return (notaFinal);
    }



    public Lugar getLugarRecuperado(){
        bundle = getIntent().getExtras();
        recuperado = (Lugar) bundle.getSerializable(getString(R.string.lugar));
        return recuperado;
    }
}









