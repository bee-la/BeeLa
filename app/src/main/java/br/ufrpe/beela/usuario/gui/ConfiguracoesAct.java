package br.ufrpe.beela.usuario.gui;

import android.content.Intent;
import android.graphics.Typeface;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import br.ufrpe.beela.gui.R;
import br.ufrpe.beela.usuario.dominio.Pessoa;

public class ConfiguracoesAct extends AppCompatActivity {
    private Button apagarButton7, alterarNomeButton5, alterarSenhaButton6, sairButton8, carregarFoto;
    private TextView nomeTextView11;
    private Pessoa pessoa=new LoginAct().getPessoa();
    private ArrayList<TextView> textos= new ArrayList<TextView>();
    private static final int RESULT_LOAD_IMAGE = 9002;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracoes);

//      ----------------------------------Alteração da fonte-------------------------------------------
        textos.add((TextView) findViewById(R.id.button5));
        textos.add((TextView) findViewById(R.id.button6));
        textos.add((TextView) findViewById(R.id.button7));
        textos.add((TextView) findViewById(R.id.button8));

        Typeface fonte = Typeface.createFromAsset(getAssets(), "fonts/Chewy.ttf");
        for (TextView txtView: textos) {
            txtView.setTypeface(fonte);
        }

        nomeTextView11=(TextView)findViewById(R.id.textView11);
        nomeTextView11.setTypeface(fonte);
        //---------------------------------Clique Trocar de tela------------------------------------
        alterarNomeButton5 =(Button) findViewById(R.id.button5);
        alterarNomeButton5.setOnClickListener(new View.OnClickListener() {@Override public void onClick (View v){alterarNomeButton5();}});


        alterarSenhaButton6 = (Button) findViewById(R.id.button6);
        alterarSenhaButton6.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) {alterarSenhaButton6();}});

        apagarButton7 = (Button) findViewById(R.id.button7);
        apagarButton7.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) {apagarButton7();}});

        sairButton8 = (Button) findViewById(R.id.button8);
        sairButton8.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) {sairButton7();}});
        setarNome();

        carregarFoto = findViewById(R.id.btUpload);
        carregarFoto.setOnClickListener(new View.OnClickListener() {

            @Override public void onClick(View v){
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE);

            }

        });
    }

    //----------------------------------Trocar Tela Funções -------------------------------------
    private void alterarNomeButton5() {
        startActivity(new Intent(ConfiguracoesAct.this, AlterarNomeAct.class));
        finish();
}

    private void alterarSenhaButton6 () {
        startActivity(new Intent(ConfiguracoesAct.this, AlterarSenhaAct.class));
        finish();
    }

    private void apagarButton7() {
        startActivity(new Intent(ConfiguracoesAct.this, ApagarContaAct.class));
    }

    private void sairButton7() {
        startActivity(new Intent(ConfiguracoesAct.this, LoginAct.class));
    }

    private void setarNome(){
        nomeTextView11.setText(pessoa.getNome());
    }
}
