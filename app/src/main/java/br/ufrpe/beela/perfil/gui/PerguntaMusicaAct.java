package br.ufrpe.beela.perfil.gui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.io.Serializable;

import java.util.ArrayList;
import br.ufrpe.beela.perfil.dominio.PerfilMusica;
import br.ufrpe.beela.perfil.dominio.PerfilUsuario;
import br.ufrpe.beela.perfil.negocio.PerfilService;
import br.ufrpe.beela.gui.R;

public class PerguntaMusicaAct extends AppCompatActivity implements Serializable{
    private PerfilUsuario perfilUsuario = new PerfilUsuario();
    private PerfilService perfilService = new PerfilService();
    private TextView pergunta;
    private Button botaoConfirmar;

    private ArrayList<CheckBox> checkBoxesMusicas = new ArrayList<>();
    private ArrayList<PerfilMusica> listaMusica = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perguntas_musica);

        alterarFonte();
        adcCheckBoxMus();
        clicarBotaoConfirmar();
    }

    private void alterarFonte() {
        Typeface fonte = Typeface.createFromAsset(getAssets(), "fonts/Chewy.ttf");
        pergunta = (TextView) findViewById(R.id.textView9);
        pergunta.setTypeface(fonte);
    }

    private void adcCheckBoxMus() {
        checkBoxesMusicas.add((CheckBox) findViewById(R.id.checkBoxRock));
        checkBoxesMusicas.add((CheckBox) findViewById(R.id.checkboxSertanejo));
        checkBoxesMusicas.add((CheckBox) findViewById(R.id.checkBoxForro));
        checkBoxesMusicas.add((CheckBox) findViewById(R.id.checkBoxSamba));
        checkBoxesMusicas.add((CheckBox) findViewById(R.id.checkBox9));

    }

    private void clicarBotaoConfirmar() {
        botaoConfirmar = (Button) findViewById(R.id.buttonConfirmar);
        botaoConfirmar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                adcListaMusica(checkBoxesMusicas, listaMusica);
            }
        });
    }

    public void adcListaMusica(ArrayList<CheckBox> checkBoxesMusicas, ArrayList<PerfilMusica> listaMusica) {
        for (CheckBox x : checkBoxesMusicas) {
            if (x.isChecked()) {
                PerfilMusica musica = new PerfilMusica();
                musica.setNome(x.getText().toString());
                listaMusica.add(musica);
            }
        }
        perfilUsuario.setMusica(listaMusica);
        irTelaPerguntaComida();
    }

    public void onCheckboxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();
    }

    private void irTelaPerguntaComida() {
        Context context = PerguntaMusicaAct.this;
        Intent intent = new Intent(context, PerguntaComidaAct.class);
        intent.putExtra("perfil", (Serializable) perfilUsuario);
        startActivity(intent);
        //startActivityForResult(new Intent(PerguntaMusicaAct.this, PerguntaComidaAct.class), 1);
         finish();

    }

//    public void salvarMusica(String nome) {
//        if (nome != null) {
//            perfilService.adcMusica(perfilUsuario,nome, this);
//        } else {
//            Toast.makeText(getApplicationContext(), "NÂO TEM NOME", Toast.LENGTH_SHORT).show();
//        }
//    }
//
//    protected void onActivityResult(int codigoDaTela, int quemInviou, Intent intent) {
//        if (codigoDaTela == 1) {
//            try {
//                Bundle valor = intent.getExtras();
//                if (valor != null) {
//                    String Nome = valor.getString("nomePerfil");
//                    salvarMusica(Nome);
//                    finish();
//                }
//            } catch (Exception e) {
//                finish();
//                e.printStackTrace();
//            }
//        }
//    }

}
