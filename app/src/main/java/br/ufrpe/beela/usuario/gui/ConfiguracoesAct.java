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
    private TextView alterar, apagarConta, alterarNome, alterarSenha, sair, carregarFoto;
    private TextView nomeTextView11;
    private Pessoa pessoa = new LoginAct().getPessoa();
    private ArrayList<TextView> textoCampos = new ArrayList<TextView>();
    private static final int RESULT_LOAD_IMAGE = 9002;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracoes);

        alterar = (TextView) findViewById(R.id.textView17);
        alterarNome = (TextView) findViewById(R.id.textView15);
        alterarSenha = (TextView) findViewById(R.id.textView16);
        sair = (TextView) findViewById(R.id.textView12);
        apagarConta = (TextView) findViewById(R.id.textView18);

        alterarFonte();
        carregarFotoGaleria();
        irAlterarNome();
        irAlterarSenha();
        irApagarConta();
        sair();
    }

    private void alterarFonte() {
        adcListaTexto();
        Typeface fonte = Typeface.createFromAsset(getAssets(), "fonts/Chewy.ttf");
        for (TextView txtView : textoCampos) {
            txtView.setTypeface(fonte);
        }
    }

    private void adcListaTexto() {
        textoCampos.add(alterar);
        textoCampos.add(alterarNome);
        textoCampos.add(alterarSenha);
        textoCampos.add(apagarConta);
        textoCampos.add(sair);
    }

    private void carregarFotoGaleria() {
        carregarFoto = findViewById(R.id.btUpload);
        carregarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE);
            }
        });
    }

    private void irAlterarNome() {
        alterarNome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ConfiguracoesAct.this, AlterarNomeAct.class));
            }
        });
    }

    private void irAlterarSenha() {
        alterarSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ConfiguracoesAct.this, AlterarSenhaAct.class));
            }
        });
    }

    private void irApagarConta() {
        apagarConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ConfiguracoesAct.this, ApagarContaAct.class));
                finish();
            }
        });
    }

    private void sair() {
        sair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ConfiguracoesAct.this, LoginAct.class));
                finish();
            }
        });
    }

}
